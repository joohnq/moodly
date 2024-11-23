package com.joohnq.moodapp.ui.presentation.expression_analysis.event

sealed class ExpressionAnalysisEvent {
    data object OnGoBack : ExpressionAnalysisEvent()
    data object OnNavigateToAddStatsRecord : ExpressionAnalysisEvent()
}
