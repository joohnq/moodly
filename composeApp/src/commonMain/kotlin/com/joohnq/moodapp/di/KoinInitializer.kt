package com.joohnq.moodapp.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

class KoinInitializer {
    fun init(config: KoinAppDeclaration? = null) =
        startKoin {
            modules(
                appModule
            )
            config?.invoke(this)
        }
}
