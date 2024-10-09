package com.joohnq.moodapp

import android.app.Application
import com.joohnq.moodapp.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class AndroidApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@AndroidApp)
            androidLogger()
        }
    }
}