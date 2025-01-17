package com.joohnq.security.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.joohnq.security.domain.SecurityAuthentication
import org.koin.compose.koinInject
import org.koin.core.parameter.parametersOf

@Composable actual fun securityAuthentication(): SecurityAuthentication {
    val appCompat = LocalContext.current as AppCompatActivity
    return koinInject { parametersOf(appCompat) }
}