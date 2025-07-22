package com.joohnq.home.impl.presentation.viewmodel

sealed interface DashboardIntent {
    data object Get : DashboardIntent
}