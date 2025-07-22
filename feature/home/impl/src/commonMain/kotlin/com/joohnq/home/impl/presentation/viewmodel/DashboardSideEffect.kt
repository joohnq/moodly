package com.joohnq.home.impl.presentation.viewmodel

sealed interface DashboardSideEffect {
    data class ShowError(val message: String) : DashboardSideEffect
}