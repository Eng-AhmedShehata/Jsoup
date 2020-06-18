package com.ashehata.jsoupapp.externals

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // start koin
        doKoin()
    }

    private fun doKoin() {
        startKoin {
            androidContext(this@MyApplication)
            modules(mModule)
        }
    }
}