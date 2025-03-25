package com.joohnq.mood.ui.viewmodel

sealed interface MoodSideEffect {
    data object StatsDeleted : MoodSideEffect
    data object StatsAdded : MoodSideEffect
    data class ShowError(val error: Throwable) : MoodSideEffect
}