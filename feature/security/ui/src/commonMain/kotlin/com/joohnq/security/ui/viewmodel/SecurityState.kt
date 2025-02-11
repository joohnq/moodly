package com.joohnq.security.ui.viewmodel

import com.joohnq.domain.entity.UiState
import com.joohnq.security.domain.Security

data class SecurityState(
    val item: UiState<Security> = UiState.Idle,
)