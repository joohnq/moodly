package com.joohnq.stress_level.ui.viewmodel

sealed interface StressLevelSideEffect {
    data object StressLevelAdded : StressLevelSideEffect
    data class ShowError(val error: Throwable) : StressLevelSideEffect
} 