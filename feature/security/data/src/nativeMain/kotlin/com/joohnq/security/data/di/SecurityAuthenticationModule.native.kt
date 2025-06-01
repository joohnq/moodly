package com.joohnq.security.data.di

import com.joohnq.security.data.SecurityAuthenticationImpl
import com.joohnq.security.domain.SecurityAuthentication
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val securityAuthenticationModule: Module = module {
    factoryOf(::SecurityAuthenticationImpl) bind SecurityAuthentication::class
}