package com.joohnq.home.impl.ui.event

sealed interface DashboardBottomNavigationEvent {
    data object AddMood : DashboardBottomNavigationEvent
    data object ToggleExpanded : DashboardBottomNavigationEvent
    data object AddSelfJournal : DashboardBottomNavigationEvent
}