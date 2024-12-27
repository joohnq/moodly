package com.joohnq.mood.di

import org.koin.core.context.startKoin

actual class KoinInitializer {
    actual fun init() {
        startKoin {
            modules(sharedModule, viewModelModule, platformModule)
        }
    }
}
