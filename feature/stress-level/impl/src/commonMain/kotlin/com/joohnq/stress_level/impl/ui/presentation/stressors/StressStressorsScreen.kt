package com.joohnq.stress_level.impl.ui.presentation.stressors

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.stress_level.impl.ui.presentation.add.AddStressLevelContract
import com.joohnq.stress_level.impl.ui.presentation.add.AddStressLevelViewModel
import com.joohnq.ui.sharedViewModel
import kotlinx.coroutines.launch

@Composable
fun StressStressorsScreen(
    onGoBack: () -> Unit,
    onNavigateToStressLevelOverview: () -> Unit,
    viewModel: AddStressLevelViewModel = sharedViewModel(),
) {
    val snackBarState = rememberSnackBarState()
    val scope = rememberCoroutineScope()
    val state by viewModel.state.collectAsState()

    fun onError(error: String) {
        scope.launch { snackBarState.showSnackbar(error) }
    }

    fun onEvent(event: AddStressLevelContract.Event) =
        when (event) {
            is AddStressLevelContract.Event.OnGoBack -> onGoBack()
            else -> Unit
        }

    LaunchedEffect(viewModel) {
        viewModel.sideEffect.collect { event ->
            when (event) {
                is AddStressLevelContract.SideEffect.PopUpToStressLevelOverview -> {
                    onNavigateToStressLevelOverview()
                }

                is AddStressLevelContract.SideEffect.ShowError -> onError(event.error)
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            viewModel.onIntent(AddStressLevelContract.Intent.ResetState)
        }
    }

    StressStressorsContent(
        snackBarState = snackBarState,
        state = state,
        onEvent = ::onEvent,
        onIntent = viewModel::onIntent
    )
}
