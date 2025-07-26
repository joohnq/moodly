package com.joohnq.moodapp

import android.app.Application
import com.joohnq.moodapp.di.KoinInitializer
import org.koin.android.ext.koin.androidContext

class AndroidApp : Application() {
    override fun onCreate() {
        super.onCreate()
        KoinInitializer().init {
            androidContext(this@AndroidApp)
        }
    }
}
