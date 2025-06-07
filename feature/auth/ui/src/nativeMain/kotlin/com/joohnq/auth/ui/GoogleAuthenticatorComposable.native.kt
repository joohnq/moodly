package com.joohnq.auth.ui

import androidx.compose.runtime.Composable
import com.joohnq.auth.domain.GoogleAuthenticator
import org.koin.compose.koinInject

@Composable
actual fun googleAuthenticatorComposable(): GoogleAuthenticator {
    return koinInject<GoogleAuthenticator>()
}