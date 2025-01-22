package com.joohnq.mood.ui.viewmodel

sealed interface StatSideEffect {
    data object StatsDeleted : StatSideEffect
    data object StatsAdded : StatSideEffect
    data class ShowError(val error: Throwable) : StatSideEffect
}