package com.joohnq.moodapp

import android.app.Application
import com.joohnq.moodapp.di.KoinInitializer

class AndroidApp : Application() {
    override fun onCreate() {
        super.onCreate()
        KoinInitializer(this).init()
    }
}