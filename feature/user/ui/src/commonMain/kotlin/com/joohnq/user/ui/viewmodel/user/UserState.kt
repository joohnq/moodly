package com.joohnq.user.ui.viewmodel.user

import com.joohnq.core.ui.entity.UiState
import com.joohnq.domain.entity.User

data class UserState(
    val user: UiState<User> = UiState.Idle,
)
