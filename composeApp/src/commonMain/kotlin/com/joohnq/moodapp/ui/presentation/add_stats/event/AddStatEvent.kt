package com.joohnq.moodapp.ui.presentation.add_stats.event

sealed class AddStatEvent {
    data object OnGoBack : AddStatEvent()
    data object OnNavigateToExpressionAnalysis : AddStatEvent()
}
