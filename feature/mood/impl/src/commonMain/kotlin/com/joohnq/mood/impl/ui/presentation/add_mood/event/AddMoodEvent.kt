package com.joohnq.mood.impl.ui.presentation.add_mood.event

sealed interface AddMoodEvent {
    data object OnGoBack : AddMoodEvent
    data object OnNavigateToExpressionAnalysis : AddMoodEvent
}
