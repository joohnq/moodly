package com.joohnq.moodapp.di

import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

actual class KoinInitializer {
    actual fun init() {
        startKoin {
            modules(
                AppModule().module,
            )
        }
    }
}

