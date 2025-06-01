package com.joohnq.mood.ui.presentation.expression_analysis

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.mood.ui.resource.mapper.toDomain
import com.joohnq.mood.ui.presentation.add_mood.viewmodel.AddMoodContract
import com.joohnq.mood.ui.presentation.add_mood.viewmodel.AddMoodViewModel
import com.joohnq.mood.ui.presentation.mood.viewmodel.MoodContract
import com.joohnq.mood.ui.presentation.mood.viewmodel.MoodViewModel
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
            ExpressionAnalysisContract.Event.Add ->
                moodViewModel.onIntent(
                    MoodContract.Intent.Add(
                        addStatsState.record.toDomain()
                    )
                )

            ExpressionAnalysisContract.Event.GoBack -> onGoBack()
        }

    LaunchedEffect(moodViewModel) {
        moodViewModel.sideEffect.collect { event ->
            when (event) {
                is MoodContract.SideEffect.MoodAdded -> {
                    moodViewModel.onIntent(MoodContract.Intent.GetAll)
                    onNavigateToMood()
                }

                is MoodContract.SideEffect.ShowError -> onError(event.error.message.toString())
                else -> {}
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            addStatsViewModel.onIntent(AddMoodContract.Intent.ResetState)
        }
    }

    ExpressionAnalysisUI(
        snackBarState = snackBarState,
        description = addStatsState.record.description,
        onEvent = ::onEvent,
        onIntent = addStatsViewModel::onIntent
    )
}
