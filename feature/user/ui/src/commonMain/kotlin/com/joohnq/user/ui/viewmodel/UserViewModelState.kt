package com.joohnq.user.ui.viewmodel

import com.joohnq.domain.entity.User
import com.joohnq.shared.ui.state.UiState

data class UserViewModelState(
    val user: UiState<User> = UiState.Idle,
    val adding: UiState<Boolean> = UiState.Idle,
    val updating: UiState<Boolean> = UiState.Idle,
)