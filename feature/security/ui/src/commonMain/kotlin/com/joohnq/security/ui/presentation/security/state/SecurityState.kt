package com.joohnq.security.ui.presentation.security.state

import androidx.compose.material3.SnackbarHostState
import com.joohnq.security.ui.presentation.security.event.SecurityEvent

data class SecurityState(
    val snackBarState: SnackbarHostState,
    val onEvent: (SecurityEvent) -> Unit,
)