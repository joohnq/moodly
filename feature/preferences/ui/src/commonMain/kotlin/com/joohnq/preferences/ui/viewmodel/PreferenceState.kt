package com.joohnq.preferences.ui.viewmodel

import com.joohnq.domain.entity.UiState
import com.joohnq.preferences.domain.entity.AppPreferences

data class PreferenceState(
    val userPreferences: UiState<AppPreferences> = UiState.Idle,
)