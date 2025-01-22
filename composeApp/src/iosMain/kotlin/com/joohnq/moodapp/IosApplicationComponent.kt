package com.joohnq.moodapp

import com.joohnq.cryptography.domain.KeyManagement
import org.jetbrains.skia.Data
import org.koin.dsl.module

data class IosApplicationComponent(
    private val keyManagement: KeyManagement<Data>,
) {
    val item = module {
        single<KeyManagement<Data>> { keyManagement }
    }
}
