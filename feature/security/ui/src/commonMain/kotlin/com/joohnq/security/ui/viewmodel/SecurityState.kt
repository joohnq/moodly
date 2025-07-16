package com.joohnq.security.ui.viewmodel

import com.joohnq.ui.entity.UiState
import com.joohnq.security.domain.Security

data class SecurityState(
    val item: UiState<Security> = UiState.Idle,
)