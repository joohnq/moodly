package com.joohnq.preferences.impl.ui.viewmodel

sealed interface PreferencesSideEffect {
    data class ShowError(val message: String) : PreferencesSideEffect
    data object UpdatedPreferences : PreferencesSideEffect
}