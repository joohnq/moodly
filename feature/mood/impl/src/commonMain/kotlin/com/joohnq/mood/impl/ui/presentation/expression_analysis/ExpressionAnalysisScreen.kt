package com.joohnq.mood.impl.ui.presentation.expression_analysis

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.mood.impl.ui.mapper.toDomain
import com.joohnq.mood.impl.ui.presentation.add_mood.AddMoodContract
import com.joohnq.mood.impl.ui.presentation.add_mood.AddMoodViewModel
import com.joohnq.mood.impl.ui.presentation.mood.MoodContract
import com.joohnq.mood.impl.ui.presentation.mood.MoodViewModel
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

    fun onEvent(event: ExpressionAnalysisContract.Event) =
        when (event) {
            ExpressionAnalysisContract.Event.OnAdd ->
                moodViewModel.onAction(
                    MoodContract.Intent.Add(
                        addStatsState.record.toDomain()
                    )
                )

            ExpressionAnalysisContract.Event.OnGoBack -> onGoBack()
        }

    LaunchedEffect(moodViewModel) {
        moodViewModel.sideEffect.collect { event ->
            when (event) {
                is MoodContract.SideEffect.StatsAdded -> {
                    moodViewModel.onAction(MoodContract.Intent.GetAll)
                    onNavigateToMood()
                }

                is MoodContract.SideEffect.ShowError -> onError(event.error)
                else -> {}
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            addStatsViewModel.onAction(AddMoodContract.Intent.ResetState)
        }
    }

    ExpressionAnalysisContent(
        snackBarState = snackBarState,
        description = addStatsState.record.description,
        onEvent = ::onEvent,
        onAddAction = addStatsViewModel::onAction
    )
}