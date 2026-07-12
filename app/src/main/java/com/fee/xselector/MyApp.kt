package com.fee.xselector

import androidx.multidex.MultiDexApplication

/**
 */
class MyApp : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(MyAppLifeCycle())
    }
}
