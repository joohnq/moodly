package com.joohnq.sleep_quality.ui.viewmodel

sealed interface SleepQualitySideEffect {
    data object SleepQualityAdded : SleepQualitySideEffect
    data class ShowError(val error: String) : SleepQualitySideEffect
} 