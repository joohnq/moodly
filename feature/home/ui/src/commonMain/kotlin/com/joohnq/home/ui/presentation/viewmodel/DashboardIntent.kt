package com.joohnq.home.ui.presentation.viewmodel

sealed interface DashboardIntent {
    data object GetData : DashboardIntent
}