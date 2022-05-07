package com.fee.xselector.ui.login

import android.app.Activity
import android.content.Context
import android.graphics.Color
import androidx.lifecycle.Observer
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.LayoutInflaterCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.fee.thexselector.DrawableSelector
import com.fee.thexselector.ShapeItem
import com.fee.thexselector.ShapeSelector
import com.fee.thexselector.XSelector
import com.fee.xselector.*

class LoginActivity : BaseActivity() {

    init {
        Log.i(TAG, "-->构造方法...")
        addOnContextAvailableListener {
            Log.i(TAG, "-->构造方法... 监听了  上下文有效")
        }
//        delegate.installViewFactory()//java.lang.IllegalStateException: System services not available to Activities before onCreate()

    }
    private lateinit var loginViewModel: LoginViewModel

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
//        layoutInflater//注意，调用这个时，此时还没有 window
//        delegate
        val factory = LayoutInflaterFactory2()
        factory.mViewDecorator = CustomTypeFaceViewDecorator(SkinViewDecorator())
        factory.replaceFactory2(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        //这里的执行 比 addOnContextAvailableListener()的回调稍早 -------------
        super.onCreate(savedInstanceState)
        //如果 在AndroidMenifest中申请了 android:largeHeap="true" 则值会不同
        // 没申请前：196608 KB
        // 如果申请了，eg.: 524288 KB
        val appCanUseMemory = Runtime.getRuntime().maxMemory().div(1024).toInt()
        Log.e("LoginActivity", "Max memory is  appCanUseMemory = $appCanUseMemory KB");
        setContentView(R.layout.activity_login)

        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val login = findViewById<Button>(R.id.login)
        XSelector.me.colorSelector()
            .stateDef(Color.YELLOW)
            .statePressed(Color.RED)
            .stateDisabled(Color.GRAY)
//            .isDefColorTransparent(false)
            .into(login)

        XSelector.drawableSelector()
            .statePressed(Color.GREEN)
            .stateDef(Color.GRAY)
            .into(login)
//            .defState(null)
            .statePressed(null)
            .into(username,true)

        ShapeItem()
            .solidColor(Color.RED)
            .corners(20f) //复用了 圆角属性
//            .gradientRadial(Color.GRAY,Color.CYAN,Color.RED,160f)
            .stroke(Color.BLACK,8,3f,3f)
            .attachSelector(DrawableSelector())
            .asStateDefOfSelector()
            .solidColor(Color.YELLOW)
            .stroke(Color.BLUE,9,3f,3f)
            .asStatePressedOfSelector()
            .into(login)

//        ShapeSelector()
//            .stateDef {
//                solidColor(Color.RED)
//                corners(20f)
//                stroke(Color.BLACK,8,3f,3f)
//            }
//            .statePressed {
//                solidColor(Color.YELLOW)
//                corners(20f)
//                stroke(Color.BLUE,8,3f,3f)
//            }
//            .stateDisabled {
//
//            }
//            .into(login)
        val loading = findViewById<ProgressBar>(R.id.loading)

        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
        })

        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)
            }
            setResult(Activity.RESULT_OK)

            //Complete and destroy login activity once successful
            finish()
        })

        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                    username.text.toString(),
                    password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                        username.text.toString(),
                        password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                                username.text.toString(),
                                password.text.toString()
                        )
                }
                false
            }

            login.setOnClickListener {
                loading.visibility = View.VISIBLE
                loginViewModel.login(username.text.toString(), password.text.toString())
            }
        }
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        // TODO : initiate successful logged in experience
        Toast.makeText(
                applicationContext,
                "$welcome $displayName",
                Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }

}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}