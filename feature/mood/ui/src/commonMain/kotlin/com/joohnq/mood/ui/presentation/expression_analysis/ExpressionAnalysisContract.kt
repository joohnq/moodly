package com.joohnq.mood.ui.presentation.expression_analysis

sealed interface ExpressionAnalysisContract {
    sealed interface Event {
        data object GoBack : Event
        data object Add : Event
    }
}