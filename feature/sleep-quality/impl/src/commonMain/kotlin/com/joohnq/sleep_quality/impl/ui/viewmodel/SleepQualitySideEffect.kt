package com.joohnq.sleep_quality.impl.ui.viewmodel

sealed interface SleepQualitySideEffect {
    data object SleepQualityAdded : SleepQualitySideEffect
    data object Deleted : SleepQualitySideEffect
    data class ShowError(val error: String) : SleepQualitySideEffect
} 