package com.joohnq.mood.impl.ui.presentation.expression_analysis.event

sealed interface ExpressionAnalysisEvent {
    data object OnGoBack : ExpressionAnalysisEvent
    data object OnAdd : ExpressionAnalysisEvent
}
