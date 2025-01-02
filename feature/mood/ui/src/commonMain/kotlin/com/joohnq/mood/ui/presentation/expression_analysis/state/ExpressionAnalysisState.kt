package com.joohnq.mood.ui.presentation.expression_analysis.state

import androidx.compose.material3.SnackbarHostState
import com.joohnq.mood.ui.presentation.add_stats.viewmodel.AddStatIntent
import com.joohnq.mood.ui.presentation.expression_analysis.event.ExpressionAnalysisEvent

data class ExpressionAnalysisState(
    val snackBarState: SnackbarHostState,
    val description: String,
    val onAddAction: (AddStatIntent) -> Unit = {},
    val onEvent: (ExpressionAnalysisEvent) -> Unit = {},
)
