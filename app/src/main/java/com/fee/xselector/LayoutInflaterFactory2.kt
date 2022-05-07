package com.fee.xselector

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewStub
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.LayoutInflaterCompat
import java.lang.reflect.Field

/**
 */
open class LayoutInflaterFactory2 : LayoutInflater.Factory2 {

    private val tag = "LayoutInflaterFactory2"

    //    protected var mDelegate: AppCompatDelegate? = null
    protected var mSrcLayoutInflater: LayoutInflater? = null

    /**
     * 在 [LayoutInflater] 中已经存在的 [LayoutInflater.Factory2]
     */
    protected var mExistFactory2: LayoutInflater.Factory2? = null

    /**
     * 在 [LayoutInflater] 中已经存在的 [LayoutInflater.Factory]
     */
    protected var mExistFactory1: LayoutInflater.Factory? = null

    /**
     * View 的 装饰器
     */
    var mViewDecorator: AbsViewDecorator? = null

    companion object Helper {
        /**
         * 辅助构建出系统常见的 View
         */
        fun createViewBaseViewName(
            parent: View?,
            name: String,
            context: Context,
            attrs: AttributeSet
        ): View? {
            val v: View? = when (name) {
                "TextView" -> {
                    TextView(context, attrs)
                }
                "EditText" -> {
                    EditText(context, attrs)
                }
                "ImageView" -> {
                    ImageView(context, attrs)
                }
                "ViewStub" -> {
                    ViewStub(context, attrs)
                }
                "Button" -> {
                    Button(context, attrs)
                }
                else -> null
            }
            return v
        }
    }

//    fun replaceFactory2OfActivity(activity: Activity) {
//        replaceFactory2(activity)
//    }
    /**
     * 替代
     * @param isNeedForceReplace 是否需要强制
     */
    fun replaceFactory2(context: Context?, isNeedForceReplace: Boolean = false) {
        if (context == null) {
            return
        }
//        if (context is AppCompatActivity) {
//            mDelegate = context.delegate
//        }
        val layoutInflater = LayoutInflater.from(context)
        mExistFactory2 = layoutInflater.factory2
        mSrcLayoutInflater = layoutInflater
        if (mExistFactory2 == null) {//防止 异常
            LayoutInflaterCompat.setFactory2(layoutInflater, this)
        } else {//此时需要 hook
            if (isNeedForceReplace) {
                hookLayoutInflaterFactory(layoutInflater)
            }
        }
    }

    protected open fun hookLayoutInflaterFactory(layoutInflater: LayoutInflater) {
        val classOfInflaterCompat = LayoutInflaterCompat::class.java
        val classOfLayoutInflater = LayoutInflater::class.java
        kotlin.runCatching {
            val sCheckedField = getFieldOfClass(classOfInflaterCompat, "sCheckedField")
            sCheckedField?.setBoolean(layoutInflater, false)
            mExistFactory1 = layoutInflater.factory
            val mFactory = getFieldOfClass(classOfLayoutInflater, "mFactory")
            mFactory?.set(layoutInflater, this)

            val mFactory2 = getFieldOfClass(classOfLayoutInflater, "mFactory2")
            mFactory2?.set(layoutInflater, this)
        }.onFailure {

        }
    }

    private fun getFieldOfClass(classOfObj: Class<*>, fieldName: String): Field? {
        return kotlin.runCatching {
            val field = classOfObj.getDeclaredField(fieldName)
            field.isAccessible = true
            field
        }.getOrNull()
    }

    /**
     * [LayoutInflater.Factory2] 接口方法
     */
    override fun onCreateView(
        parent: View?,
        name: String,
        context: Context,
        attrs: AttributeSet
    ): View? {
        val v = combineCreateView(parent, name, context, attrs)
        onViewCreated(v, attrs)
        return v
    }

    /**
     * [LayoutInflater.Factory] 接口方法
     */
    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        val v = combineCreateView(null, name, context, attrs)
        onViewCreated(v,attrs)
        return v
    }

    /**
     * 综合的 创建 View
     */
    open fun combineCreateView(
        parent: View?,
        name: String,
        context: Context,
        attrs: AttributeSet
    ): View? {
        var v = createViewBaseViewName(parent, name, context, attrs)
        if (v == null) {
//            v = mDelegate?.createView(parent, name, context, attrs)// 这个大多数情况为 null
//            Log.i(tag, "--> combineCreateView()  mDelegate = $mDelegate, v = $v")
            mExistFactory2?.onCreateView(parent, name, context, attrs)
        }
        if (v == null) {
            mExistFactory1?.onCreateView(name, context, attrs)
        }
        if (v == null) {
            v = createViewBySrcLayoutInflater(parent, name, context, attrs)
        }
        Log.i(tag, "--> combineCreateView() name = $name, v = $v")
        return v
    }

    /**
     * [android.view.LayoutInflater.Factory] 方案无效后，仍然使用 源 LayoutInflater 进行创建 View
     * @param parent
     * @param name
     * @param context
     * @param attrs
     * @return
     */
    @SuppressLint("NewApi")
    protected open fun createViewBySrcLayoutInflater(
        parent: View?,
        name: String,
        context: Context,
        attrs: AttributeSet
    ): View? {
        var v: View? = null
        val sdkInt = Build.VERSION.SDK_INT
        val srcLayoutInflater = mSrcLayoutInflater
        if (srcLayoutInflater != null) {
            if (sdkInt >= 29) {
                if (parent != null) {
                    try {
                        v = srcLayoutInflater.onCreateView(context, parent, name, attrs)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                } else {
                    try {
                        v = srcLayoutInflater.createView(context, name, null, attrs)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            } else {
                try {
                    val viewPrefixName: String = checkOutViewPrefixByName(name)
                    v = srcLayoutInflater.createView(name, viewPrefixName, attrs)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        return v
    }

    protected open fun checkOutViewPrefixByName(viewName: String): String {
        return if (!viewName.contains(".")) { //没有 "xx.xx.xxView,一般表示 系统的 View
            if ("View" == viewName) {
                "android.view."
            } else "android.widget."
        } else ""
    }

    open fun onViewCreated(view: View?,attrs: AttributeSet?) {
        view?.let {
//            mViewDecorator?.decorateView(theView = it, attrs)
            mViewDecorator?.doDecorateView(theView = it, attrs)

        }
    }
}