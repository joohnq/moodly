package com.joohnq.auth.domain.di

import com.joohnq.auth.domain.use_case.GetAuthUserUseCase
import com.joohnq.auth.domain.use_case.SignInWithEmailAndPasswordUseCase
import com.joohnq.auth.domain.use_case.SignInWithGoogleUseCase
import com.joohnq.auth.domain.use_case.SignOutUseCase
import com.joohnq.auth.domain.use_case.SignUpWithEmailAndPasswordUseCase
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val authDomainModule: Module = module {
    singleOf(::GetAuthUserUseCase)
    singleOf(::SignInWithEmailAndPasswordUseCase)
    singleOf(::SignOutUseCase)
    singleOf(::SignUpWithEmailAndPasswordUseCase)
    singleOf(::SignInWithGoogleUseCase)
}