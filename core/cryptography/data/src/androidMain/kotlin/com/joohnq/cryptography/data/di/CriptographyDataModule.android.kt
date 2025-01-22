package com.joohnq.cryptography.data.di

import com.joohnq.cryptography.data.KeyManagementAndroid
import com.joohnq.cryptography.domain.KeyManagement
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import java.security.KeyStore

actual val cryptographyDataModule: Module = module {
    single<KeyStore> {
        KeyStore
            .getInstance("AndroidKeyStore")
            .apply {
                load(null)
            }
    }
    singleOf(::KeyManagementAndroid) bind KeyManagement::class
}