package com.joohnq.security.impl.ui

import androidx.compose.runtime.Composable
import com.joohnq.security.api.SecurityAuthentication
import org.koin.compose.koinInject

@Composable
actual fun securityAuthentication(): SecurityAuthentication = koinInject()
