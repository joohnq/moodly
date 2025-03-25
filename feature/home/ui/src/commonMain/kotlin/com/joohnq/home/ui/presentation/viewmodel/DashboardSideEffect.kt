package com.joohnq.home.ui.presentation.viewmodel

sealed interface DashboardSideEffect {
    data class ShowError(val error: Throwable) : DashboardSideEffect
}