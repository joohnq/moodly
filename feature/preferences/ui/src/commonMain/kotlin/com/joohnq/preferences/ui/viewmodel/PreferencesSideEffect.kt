package com.joohnq.preferences.ui.viewmodel

sealed interface PreferencesSideEffect {
    data class ShowError(val error: Throwable) : PreferencesSideEffect
    data object UpdatedPreferences : PreferencesSideEffect
}