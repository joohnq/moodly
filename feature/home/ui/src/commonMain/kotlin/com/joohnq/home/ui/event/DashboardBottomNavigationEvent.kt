package com.joohnq.home.ui.event

sealed class DashboardBottomNavigationEvent {
    data object AddMood : DashboardBottomNavigationEvent()
    data object ToggleExpanded : DashboardBottomNavigationEvent()
    data object AddHealthJournal : DashboardBottomNavigationEvent()
}