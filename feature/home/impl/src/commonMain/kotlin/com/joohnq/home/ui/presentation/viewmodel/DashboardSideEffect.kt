package com.joohnq.home.ui.presentation.viewmodel

sealed interface DashboardSideEffect {
    data class ShowError(val message: String) : DashboardSideEffect
}