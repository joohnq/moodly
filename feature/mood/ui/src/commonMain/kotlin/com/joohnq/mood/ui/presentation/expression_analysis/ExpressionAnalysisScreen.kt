package com.joohnq.mood.ui.presentation.expression_analysis

import androidx.compose.runtime.*
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.mood.ui.mapper.toDomain
import com.joohnq.mood.ui.presentation.add_stats.viewmodel.AddStatIntent
import com.joohnq.mood.ui.presentation.add_stats.viewmodel.AddStatViewModel
import com.joohnq.mood.ui.presentation.expression_analysis.event.ExpressionAnalysisEvent
import com.joohnq.mood.ui.viewmodel.StatSideEffect
import com.joohnq.mood.ui.viewmodel.StatsIntent
import com.joohnq.mood.ui.viewmodel.StatsViewModel
import com.joohnq.shared_resources.remember.rememberSnackBarState
import kotlinx.coroutines.launch

@Composable
fun ExpressionAnalysisScreen(
    onNavigateToMood: () -> Unit,
    onGoBack: () -> Unit,
) {
    val statsViewModel: StatsViewModel = sharedViewModel()
    val addStatsViewModel: AddStatViewModel = sharedViewModel()
    val scope = rememberCoroutineScope()
    val snackBarState = rememberSnackBarState()
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

    DisposableEffect(Unit) {
        onDispose {
            addStatsViewModel.onAction(AddStatIntent.ResetState)
        }
    }

    ExpressionAnalysisUI(
        snackBarState = snackBarState,
        description = addStatsState.description,
        onEvent = ::onEvent,
        onAddAction = addStatsViewModel::onAction
    )
}
