package com.joohnq.user.ui.viewmodel.user

import com.joohnq.core.ui.entity.UiState
import com.joohnq.domain.entity.User

data class UserViewModelState(
    val user: UiState<User> = UiState.Idle,
    val adding: UiState<Boolean> = UiState.Idle,
    val updating: UiState<Boolean> = UiState.Idle,
)
