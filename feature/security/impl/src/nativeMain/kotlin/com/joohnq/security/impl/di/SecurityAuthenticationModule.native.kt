package com.joohnq.security.impl.di

import com.joohnq.security.api.SecurityAuthentication
import com.joohnq.security.impl.data.SecurityAuthenticationImpl
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val securityAuthenticationModule: Module =
    module {
        factoryOf(::SecurityAuthenticationImpl) bind SecurityAuthentication::class
    }
