package com.joohnq.security.domain.di

import com.joohnq.security.domain.use_case.GetSecurityUseCase
import com.joohnq.security.domain.use_case.UpdateSecurityUseCase
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val securityDomainModule: Module = module {
    factoryOf(::GetSecurityUseCase)
    factoryOf(::UpdateSecurityUseCase)
}