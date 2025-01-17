package com.joohnq.stress_level.ui.viewmodel

sealed class StressLevelSideEffect {
    data object StressLevelAdded : StressLevelSideEffect()
    data class ShowError(val error: Throwable) : StressLevelSideEffect()
} 