package com.joohnq.security.ui.viewmodel

import com.joohnq.core.ui.entity.UiState
import com.joohnq.security.domain.Security

data class SecurityState(
    val item: UiState<Security> = UiState.Idle,
    val updating: Security = Security.None,
)