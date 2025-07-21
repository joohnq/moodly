package com.joohnq.preferences.impl.ui.viewmodel

import com.joohnq.ui.entity.UiState
import com.joohnq.preferences.domain.entity.AppPreferences

data class PreferenceState(
    val userPreferences: UiState<AppPreferences> = UiState.Idle,
)