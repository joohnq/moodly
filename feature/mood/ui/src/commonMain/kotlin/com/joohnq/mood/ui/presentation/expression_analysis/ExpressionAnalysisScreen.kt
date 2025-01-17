package com.joohnq.mood.ui.presentation.expression_analysis

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.core.ui.CustomScreen
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.mood.ui.mapper.toDomain
import com.joohnq.mood.ui.presentation.add_stats.viewmodel.AddStatIntent
import com.joohnq.mood.ui.presentation.add_stats.viewmodel.AddStatViewModel
import com.joohnq.mood.ui.presentation.expression_analysis.event.ExpressionAnalysisEvent
import com.joohnq.mood.ui.presentation.expression_analysis.state.ExpressionAnalysisState
import com.joohnq.mood.ui.viewmodel.StatSideEffect
import com.joohnq.mood.ui.viewmodel.StatsIntent
import com.joohnq.mood.ui.viewmodel.StatsViewModel
import kotlinx.coroutines.launch

class ExpressionAnalysisScreen(
    private val onNavigateToMood: () -> Unit,
    private val onGoBack: () -> Unit,
) :
    CustomScreen<ExpressionAnalysisState>() {
    @Composable
    override fun Screen(): ExpressionAnalysisState {
        val statsViewModel: StatsViewModel = sharedViewModel()
        val addStatsViewModel: AddStatViewModel = sharedViewModel()
        val scope = rememberCoroutineScope()
        val snackBarState = remember { SnackbarHostState() }
        val addStatsState by addStatsViewModel.state.collectAsState()

        fun onError(error: Throwable) =
            scope.launch { snackBarState.showSnackbar(error.message.toString()) }

        fun onEvent(event: ExpressionAnalysisEvent) =
            when (event) {
                ExpressionAnalysisEvent.OnAdd ->
                    statsViewModel.onAction(
                        StatsIntent.AddStatsRecord(
                            StatsRecord(
                                mood = addStatsState.mood.toDomain(),
                                description = addStatsState.description
                            )
                        )
                    )

                ExpressionAnalysisEvent.OnGoBack -> onGoBack()
            }

        LaunchedEffect(statsViewModel) {
            scope.launch {
                statsViewModel.sideEffect.collect { event ->
                    when (event) {
                        is StatSideEffect.StatsAdded -> {
                            statsViewModel.onAction(StatsIntent.GetStatsRecords)
                            onNavigateToMood()
                        }

                        is StatSideEffect.ShowError -> onError(event.error)
                        else -> {}
                    }
                }
            }
        }

        DisposableEffect(Unit) {
            onDispose {
                addStatsViewModel.onAction(AddStatIntent.ResetState)
            }
        }

        return ExpressionAnalysisState(
            snackBarState = snackBarState,
            description = addStatsState.description,
            onEvent = ::onEvent,
            onAddAction = addStatsViewModel::onAction
        )
    }

    @Composable
    override fun UI(state: ExpressionAnalysisState) = ExpressionAnalysisUI(state)
}
