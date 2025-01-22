package com.joohnq.cryptography.data.di

import com.joohnq.cryptography.data.CryptoCypher
import dev.whyoleg.cryptography.CryptographyProvider
import dev.whyoleg.cryptography.algorithms.AES
import dev.whyoleg.cryptography.algorithms.AES.Key
import dev.whyoleg.cryptography.materials.key.KeyGenerator
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val cryptographyDataModuleShared: Module = module {
    single<CryptographyProvider> { CryptographyProvider.Default }
    single<AES.GCM> { get<CryptographyProvider>().get(AES.GCM) }
    single<KeyGenerator<AES.GCM.Key>> { get<AES.GCM>().keyGenerator(Key.Size.B256) }
    singleOf(::CryptoCypher)
}