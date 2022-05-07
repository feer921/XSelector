package com.fee.xselector

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

/**
 */
open class BaseActivity : AppCompatActivity() {
    protected val TAG = javaClass.simpleName

    protected var isLifeCycleDebug: Boolean = true

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
        if (isLifeCycleDebug) {
            i(TAG, "--> attachBaseContext() newBase = $newBase")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        if (isLifeCycleDebug) {
            i(TAG, "--> onCreate() savedInstanceState = $savedInstanceState")
        }
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        if (isLifeCycleDebug) {
            i(TAG, "--> onStart()")
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (isLifeCycleDebug) {
            i(TAG, "--> onAttachedToWindow()")
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        if (isLifeCycleDebug) {
            i(TAG, "--> onDetachedFromWindow()")
        }
    }

    override fun onEnterAnimationComplete() {
        super.onEnterAnimationComplete()
        if (isLifeCycleDebug) {
            i(TAG, "--> onEnterAnimationComplete()")
        }
    }


    override fun onResume() {
        super.onResume()
        if (isLifeCycleDebug) {
            i(TAG, "--> onResume()")
        }
    }

    override fun onPause() {
        super.onPause()
        if (isLifeCycleDebug) {
            i(TAG, "--> onPause()")
        }
    }

    override fun onStop() {
        super.onStop()
        if (isLifeCycleDebug) {
            i(TAG, "--> onStop()")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (isLifeCycleDebug) {
            i(TAG, "--> onSaveInstanceState() outState = $outState")
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (isLifeCycleDebug) {
            i(TAG, "--> onRestoreInstanceState() savedInstanceState = $savedInstanceState")
        }
    }

    override fun onRestart() {
        super.onRestart()
        if (isLifeCycleDebug) {
            i(TAG, "--> onRestart()")
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (isLifeCycleDebug) {
            i(TAG, "--> onNewIntent() intent = $intent")
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        if (isLifeCycleDebug) {
            i(TAG, "--> onDestroy()")
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (isLifeCycleDebug) {
            i(TAG, "--> onBackPressed()")
        }
    }



    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (isLifeCycleDebug) {
            i(TAG, "--> onConfigurationChanged() newConfig = $newConfig")
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (isLifeCycleDebug) {
            i(TAG, "--> onWindowFocusChanged() hasFocus = $hasFocus")
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (isLifeCycleDebug) {
            i(
                TAG,
                "--> onActivityResult() requestCode = $requestCode,resultCode = $resultCode, data = $data "
            )
        }
    }

    override fun overridePendingTransition(enterAnim: Int, exitAnim: Int) {
        super.overridePendingTransition(enterAnim, exitAnim)
        if (isLifeCycleDebug) {
            i(TAG, "--> overridePendingTransition() enterAnim = $enterAnim,exitAnim = $exitAnim")
        }
    }






    protected fun i(logTag: String, logMsg: String) {
        Log.i(logTag, logMsg)
    }
}