package com.joohnq.security.impl.data.di

import com.joohnq.security.api.SecurityPreference
import com.joohnq.security.impl.data.SecurityPreferenceImpl
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val securityDataModule: Module =
    module {
        singleOf(::SecurityPreferenceImpl) bind SecurityPreference::class
    }
