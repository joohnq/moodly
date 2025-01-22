package com.joohnq.security.data.di

import com.joohnq.security.data.UserSecurityPreferenceImpl
import com.joohnq.security.domain.UserSecurityPreference
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val securityDataModule: Module = module {
    singleOf(::UserSecurityPreferenceImpl) bind UserSecurityPreference::class
}