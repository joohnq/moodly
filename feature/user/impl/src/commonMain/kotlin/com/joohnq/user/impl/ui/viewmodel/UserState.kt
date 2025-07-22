package com.joohnq.user.impl.ui.viewmodel

import com.joohnq.ui.entity.UiState
import com.joohnq.domain.entity.User

data class UserState(
    val user: UiState<User> = UiState.Idle,
)
