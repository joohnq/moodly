package com.joohnq.mood.impl.ui.presentation.expression_analysis

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.mood.impl.ui.mapper.toDomain
import com.joohnq.mood.impl.ui.presentation.add_mood.viewmodel.AddMoodIntent
import com.joohnq.mood.impl.ui.presentation.add_mood.viewmodel.AddMoodViewModel
import com.joohnq.mood.impl.ui.presentation.expression_analysis.event.ExpressionAnalysisEvent
import com.joohnq.mood.impl.ui.viewmodel.MoodIntent
import com.joohnq.mood.impl.ui.viewmodel.MoodSideEffect
import com.joohnq.mood.impl.ui.viewmodel.MoodViewModel
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.ui.sharedViewModel
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

    fun onError(error: String) =
        scope.launch { snackBarState.showSnackbar(error) }

    fun onEvent(event: ExpressionAnalysisEvent) =
        when (event) {
            ExpressionAnalysisEvent.OnAdd ->
                moodViewModel.onAction(
                    MoodIntent.Add(
                        addStatsState.record.toDomain()
                    )
                )

            ExpressionAnalysisEvent.OnGoBack -> onGoBack()
        }

    LaunchedEffect(moodViewModel) {
        moodViewModel.sideEffect.collect { event ->
            when (event) {
                is MoodSideEffect.StatsAdded -> {
                    moodViewModel.onAction(MoodIntent.GetAll)
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
