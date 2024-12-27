package com.joohnq.mood

import android.app.Application
import com.joohnq.mood.di.KoinInitializer

class AndroidApp : Application() {
    override fun onCreate() {
        super.onCreate()
        KoinInitializer(this).init()
    }
}