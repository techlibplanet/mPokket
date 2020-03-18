package com.mpokket

import android.app.Application
import com.mpokket.koindi.myModule
import com.mpokket.koindi.retrofitModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class MPokketApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidContext(this@MPokketApplication)
            modules(listOf(myModule, retrofitModule))
        }
    }
}