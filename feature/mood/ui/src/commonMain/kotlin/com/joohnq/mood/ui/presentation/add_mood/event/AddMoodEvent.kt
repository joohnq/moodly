package com.joohnq.mood.ui.presentation.add_mood.event

sealed interface AddMoodEvent {
    data object OnGoBack : AddMoodEvent
    data object OnNavigateToExpressionAnalysis : AddMoodEvent
}
