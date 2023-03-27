package com.ashish.royalmobileadminapp

import android.app.Application
import com.onesignal.OneSignal

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)

        OneSignal.initWithContext(this)
        OneSignal.setAppId("2a3048a8-6b9a-497e-aa33-91c27fd83db7")
    }
}