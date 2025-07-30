package com.joohnq.stress_level.impl.ui.presentation.add

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.stress_level.impl.ui.resource.StressLevelResource
import com.joohnq.ui.sharedViewModel
import kotlinx.coroutines.launch

@Composable
fun AddStressLevelScreen(
    onNavigateToStressStressors: () -> Unit,
    onGoBack: () -> Unit,
    viewModel: AddStressLevelViewModel = sharedViewModel(),
) {
    val snackBarState = rememberSnackBarState()
    val scope = rememberCoroutineScope()
    val state by viewModel.state.collectAsState()

    fun onError(error: String) = scope.launch { snackBarState.showSnackbar(error) }

    fun onEvent(event: AddStressLevelContract.Event) {
        when (event) {
            AddStressLevelContract.Event.OnGoBack -> onGoBack()
            AddStressLevelContract.Event.OnContinue -> {
                if (state.record.stressLevel != StressLevelResource.One) {
                    onNavigateToStressStressors()
                    return
                }

                viewModel.onIntent(
                    AddStressLevelContract.Intent.Add
                )
            }

            AddStressLevelContract.Event.OnPopUpToStressLevelOverview -> onGoBack()
        }
    }

    LaunchedEffect(viewModel) {
        viewModel.sideEffect.collect { event ->
            when (event) {
                is AddStressLevelContract.SideEffect.PopUpToStressLevelOverview -> {
                    onEvent(AddStressLevelContract.Event.OnPopUpToStressLevelOverview)
                }

                is AddStressLevelContract.SideEffect.ShowError -> onError(event.error)
            }
        }
    }

    AddStressLevelScreenContent(
        snackBarState = snackBarState,
        state = state,
        onEvent = ::onEvent,
        onIntent = viewModel::onIntent
    )
}
