package com.joohnq.security.impl.data.di

import androidx.appcompat.app.AppCompatActivity
import com.joohnq.security.api.SecurityAuthentication
import com.joohnq.security.impl.data.SecurityAuthenticationImpl
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

actual val securityAuthenticationModule: Module =
    module {
        factory { (activity: AppCompatActivity) ->
            SecurityAuthenticationImpl(activity)
        } bind
                SecurityAuthentication::class
    }