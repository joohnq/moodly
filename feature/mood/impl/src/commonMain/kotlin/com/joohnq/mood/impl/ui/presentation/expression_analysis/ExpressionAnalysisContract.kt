package com.joohnq.mood.impl.ui.presentation.expression_analysis

sealed interface ExpressionAnalysisContract {
    sealed interface Event {
        data object OnGoBack : Event

        data object OnAdd : Event
    }
}
