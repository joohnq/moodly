package com.joohnq.mood.impl.ui.viewmodel

sealed interface MoodSideEffect {
    data object StatsDeleted : MoodSideEffect
    data object StatsAdded : MoodSideEffect
    data object Updated : MoodSideEffect
    data class ShowError(val error: String) : MoodSideEffect
}