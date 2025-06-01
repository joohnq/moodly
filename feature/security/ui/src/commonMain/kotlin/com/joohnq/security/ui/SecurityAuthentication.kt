package com.joohnq.security.ui

import androidx.compose.runtime.Composable
import com.joohnq.security.domain.SecurityAuthentication

@Composable
expect fun securityAuthentication(): SecurityAuthentication
