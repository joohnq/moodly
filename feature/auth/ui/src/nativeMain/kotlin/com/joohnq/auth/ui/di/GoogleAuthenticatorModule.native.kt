package com.joohnq.auth.ui.di

import org.koin.core.module.Module
import org.koin.dsl.module
import com.joohnq.auth.domain.GoogleAuthenticator
import com.joohnq.auth.ui.GoogleAuthenticatorImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind

actual val googleAuthenticatorModule: Module = module {
    singleOf(::GoogleAuthenticatorImpl) bind GoogleAuthenticator::class
}