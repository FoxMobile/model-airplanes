package com.modelairplanes

import android.app.Application
import com.google.firebase.analytics.FirebaseAnalytics
import com.modelairplanes.di.dataModule
import com.modelairplanes.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initFirebase()

        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@MainApplication)
            // Load modules
            modules(listOf(dataModule, viewModelModule))
        }

    }

    private fun initFirebase() {
        FirebaseAnalytics.getInstance(this)
    }
}