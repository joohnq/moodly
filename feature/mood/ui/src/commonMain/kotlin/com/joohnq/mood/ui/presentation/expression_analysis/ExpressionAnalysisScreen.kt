package com.joohnq.mood.ui.presentation.expression_analysis

import androidx.compose.runtime.*
import com.joohnq.ui.sharedViewModel
import com.joohnq.mood.ui.mapper.toDomain
import com.joohnq.mood.ui.presentation.add_mood.viewmodel.AddMoodIntent
import com.joohnq.mood.ui.presentation.add_mood.viewmodel.AddMoodViewModel
import com.joohnq.mood.ui.presentation.expression_analysis.event.ExpressionAnalysisEvent
import com.joohnq.mood.ui.viewmodel.MoodIntent
import com.joohnq.mood.ui.viewmodel.MoodSideEffect
import com.joohnq.mood.ui.viewmodel.MoodViewModel
import com.joohnq.shared_resources.remember.rememberSnackBarState
import kotlinx.coroutines.launch

@Composable
fun ExpressionAnalysisScreen(
    onNavigateToMood: () -> Unit,
    onGoBack: () -> Unit,
) {
    val moodViewModel: MoodViewModel = sharedViewModel()
    val addStatsViewModel: AddMoodViewModel = sharedViewModel()
    val scope = rememberCoroutineScope()
    val snackBarState = rememberSnackBarState()
    val addStatsState by addStatsViewModel.state.collectAsState()

    fun onError(error: Throwable) =
        scope.launch { snackBarState.showSnackbar(error.message.toString()) }

    fun onEvent(event: ExpressionAnalysisEvent) =
        when (event) {
            ExpressionAnalysisEvent.OnAdd ->
                moodViewModel.onAction(
                    MoodIntent.AddMoodRecord(
                        addStatsState.record.toDomain()
                    )
                )

            ExpressionAnalysisEvent.OnGoBack -> onGoBack()
        }

    LaunchedEffect(moodViewModel) {
        moodViewModel.sideEffect.collect { event ->
            when (event) {
                is MoodSideEffect.StatsAdded -> {
                    moodViewModel.onAction(MoodIntent.GetMoodRecords)
                    onNavigateToMood()
                }

                is MoodSideEffect.ShowError -> onError(event.error)
                else -> {}
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            addStatsViewModel.onAction(AddMoodIntent.ResetState)
        }
    }

    ExpressionAnalysisUI(
        snackBarState = snackBarState,
        description = addStatsState.record.description,
        onEvent = ::onEvent,
        onAddAction = addStatsViewModel::onAction
    )
}
