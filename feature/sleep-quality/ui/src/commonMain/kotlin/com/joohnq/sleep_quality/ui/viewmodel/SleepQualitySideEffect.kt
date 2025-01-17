package com.joohnq.sleep_quality.ui.viewmodel

sealed class SleepQualitySideEffect {
    data object SleepQualityAdded : SleepQualitySideEffect()
    data class ShowError(val error: Throwable) : SleepQualitySideEffect()
} 