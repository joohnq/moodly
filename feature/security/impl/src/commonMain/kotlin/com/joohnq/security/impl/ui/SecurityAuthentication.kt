package com.joohnq.security.ui

import androidx.compose.runtime.Composable
import com.joohnq.security.api.SecurityAuthentication

@Composable
expect fun securityAuthentication(): SecurityAuthentication
