package com.fee.xselector

import android.app.Application

/**
 */
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(MyAppLifeCycle())
    }
}