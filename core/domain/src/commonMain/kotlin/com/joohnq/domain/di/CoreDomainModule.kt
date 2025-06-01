package com.joohnq.domain.di

import com.joohnq.domain.validation.EmailValidator
import com.joohnq.domain.validation.NameValidator
import com.joohnq.domain.validation.PasswordValidator
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val coreDomainModule: Module = module{
    singleOf(::NameValidator)
    singleOf(::EmailValidator)
    singleOf(::PasswordValidator)
}