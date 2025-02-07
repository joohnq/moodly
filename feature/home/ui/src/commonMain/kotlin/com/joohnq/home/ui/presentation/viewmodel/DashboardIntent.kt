package com.joohnq.home.ui.presentation.viewmodel

sealed interface DashboardIntent {
    data object Get : DashboardIntent
}