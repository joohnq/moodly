package com.joohnq.user.ui.viewmodel.user_preferences

import com.joohnq.core.ui.entity.UiState
import com.joohnq.domain.entity.UserPreferences

data class UserPreferenceState(
    val userPreferences: UiState<UserPreferences> = UiState.Idle,
)