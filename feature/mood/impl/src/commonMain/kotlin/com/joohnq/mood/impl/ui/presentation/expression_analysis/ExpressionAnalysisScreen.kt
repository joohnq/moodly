package com.joohnq.mood.impl.ui.presentation.expression_analysis

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.mood.impl.ui.presentation.add.AddMoodContract
import com.joohnq.mood.impl.ui.presentation.add.AddMoodViewModel
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.ui.sharedViewModel
import kotlinx.coroutines.launch

@Composable
fun ExpressionAnalysisScreen(
    onNavigateToMood: () -> Unit,
    onGoBack: () -> Unit,
    addMoodViewModel: AddMoodViewModel = sharedViewModel(),
) {
    val scope = rememberCoroutineScope()
    val snackBarState = rememberSnackBarState()
    val addStatsState by addMoodViewModel.state.collectAsState()

    fun onError(error: String) = scope.launch { snackBarState.showSnackbar(error) }

    fun onEvent(event: AddMoodContract.Event) =
        when (event) {
            AddMoodContract.Event.OnGoBack -> onGoBack()
            else -> {}
        }

    LaunchedEffect(addMoodViewModel) {
        addMoodViewModel.sideEffect.collect { event ->
            when (event) {
                is AddMoodContract.SideEffect.ShowError -> onError(event.error)
                AddMoodContract.SideEffect.StatsAdded -> onNavigateToMood()
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            addMoodViewModel.onIntent(AddMoodContract.Intent.ResetState)
        }
    }

    ExpressionAnalysisContent(
        snackBarState = snackBarState,
        description = addStatsState.record.description,
        onEvent = ::onEvent,
        onIntent = addMoodViewModel::onIntent
    )
}
