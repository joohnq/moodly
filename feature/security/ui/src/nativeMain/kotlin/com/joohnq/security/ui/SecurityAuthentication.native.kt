package com.joohnq.security.ui

import androidx.compose.runtime.Composable
import com.joohnq.security.domain.SecurityAuthentication
import org.koin.compose.koinInject

@Composable
actual fun securityAuthentication(): SecurityAuthentication = koinInject()