package com.joohnq.auth.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.credentials.CredentialManager
import com.joohnq.auth.domain.GoogleAuthenticator
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

@Composable
actual fun googleAuthenticatorComposable(): GoogleAuthenticator {
    val context: Context = LocalContext.current
    val credentialManager: CredentialManager = CredentialManager.create(context)
    return koinInject<GoogleAuthenticator> {
        parametersOf(
            context,
            credentialManager
        )
    }
}