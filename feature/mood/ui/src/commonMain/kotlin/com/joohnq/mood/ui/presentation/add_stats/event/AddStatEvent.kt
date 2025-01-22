package com.joohnq.mood.ui.presentation.add_stats.event

sealed interface AddStatEvent {
    data object OnGoBack : AddStatEvent
    data object OnNavigateToExpressionAnalysis : AddStatEvent
}
