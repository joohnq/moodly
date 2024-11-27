package com.joohnq.moodapp.ui.presentation.expression_analysis.state

import androidx.compose.material3.SnackbarHostState
import com.joohnq.moodapp.ui.presentation.expression_analysis.event.ExpressionAnalysisEvent
import com.joohnq.moodapp.ui.presentation.add_stats.AddStatIntent

data class ExpressionAnalysisState(
    val snackBarState: SnackbarHostState,
    val description: String,
    val onAddAction: (AddStatIntent) -> Unit = {},
    val onEvent: (ExpressionAnalysisEvent) -> Unit = {},
)
