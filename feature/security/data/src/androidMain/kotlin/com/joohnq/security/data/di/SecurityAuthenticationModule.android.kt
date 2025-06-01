package com.joohnq.security.data.di

import androidx.appcompat.app.AppCompatActivity
import com.joohnq.security.data.SecurityAuthenticationImpl
import com.joohnq.security.domain.SecurityAuthentication
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

actual val securityAuthenticationModule: Module = module {
    factory { (activity: AppCompatActivity) -> SecurityAuthenticationImpl(activity) } bind SecurityAuthentication::class
}