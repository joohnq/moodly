package com.joohnq.auth.ui.di

import android.content.Context
import androidx.credentials.CredentialManager
import com.joohnq.auth.domain.GoogleAuthenticator
import com.joohnq.auth.ui.GoogleAuthenticatorImpl
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

actual val googleAuthenticatorModule: Module = module {
    single { (context: Context, credentialManager: CredentialManager) ->
        GoogleAuthenticatorImpl(
            context = context,
            credentialManager = credentialManager
        )
    } bind GoogleAuthenticator::class
}