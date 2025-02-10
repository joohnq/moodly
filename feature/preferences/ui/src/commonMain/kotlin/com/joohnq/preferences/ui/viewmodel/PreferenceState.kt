package com.joohnq.preferences.ui.viewmodel

import com.joohnq.core.ui.entity.UiState
import com.joohnq.preferences.domain.entity.AppPreferences

data class PreferenceState(
    val userPreferences: UiState<AppPreferences> = UiState.Idle,
)