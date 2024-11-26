package com.joohnq.moodapp.ui.presentation.expression_analysis

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.ui.CustomScreen
import com.joohnq.moodapp.ui.presentation.expression_analysis.event.ExpressionAnalysisEvent
import com.joohnq.moodapp.ui.presentation.expression_analysis.state.ExpressionAnalysisState
import com.joohnq.moodapp.ui.presentation.mood.MoodScreen
import com.joohnq.moodapp.ui.state.UiState.Companion.fold
import com.joohnq.moodapp.viewmodel.StatsIntent
import com.joohnq.moodapp.viewmodel.StatsViewModel
import kotlinx.coroutines.launch

class ExpressionAnalysisScreen : CustomScreen<ExpressionAnalysisState>() {
    @Composable
    override fun Screen(): ExpressionAnalysisState {
        val statsViewModel: StatsViewModel = sharedViewModel()
        val scope = rememberCoroutineScope()
        val snackBarState = remember { SnackbarHostState() }
        val statsState by statsViewModel.statsState.collectAsState()

        fun onEvent(event: ExpressionAnalysisEvent) =
            when (event) {
                ExpressionAnalysisEvent.OnNavigateToAddStatsRecord ->
                    statsViewModel.onAction(StatsIntent.AddStatsRecord())

                ExpressionAnalysisEvent.OnGoBack -> onGoBack()
            }

        LaunchedEffect(statsState.adding.status) {
            statsState.adding.status.fold(
                onError = { error -> scope.launch { snackBarState.showSnackbar(error) } },
                onSuccess = {
                    onNavigate(MoodScreen())
                    statsViewModel.onAction(StatsIntent.ResetAdding)
                    statsViewModel.onAction(StatsIntent.GetStatsRecord)
                },
            )
        }

        return ExpressionAnalysisState(
            snackBarState = snackBarState,
            description = statsState.adding.description,
            onAction = statsViewModel::onAction,
            onEvent = ::onEvent
        )
    }

    @Composable
    override fun UI(state: ExpressionAnalysisState) = ExpressionAnalysisUI(state)
}
