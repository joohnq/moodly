package com.joohnq.mood.ui.presentation.expression_analysis.event

sealed class ExpressionAnalysisEvent {
    data object OnGoBack : ExpressionAnalysisEvent()
    data object OnAdd : ExpressionAnalysisEvent()
}
