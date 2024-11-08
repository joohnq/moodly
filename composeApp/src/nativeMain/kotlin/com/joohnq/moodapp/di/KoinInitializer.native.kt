package com.joohnq.moodapp.di

import org.koin.core.context.startKoin

actual class KoinInitializer {
    actual fun init() {
        startKoin {
            modules(sharedModule, viewModelModule, platformModule)
        }
    }
}

