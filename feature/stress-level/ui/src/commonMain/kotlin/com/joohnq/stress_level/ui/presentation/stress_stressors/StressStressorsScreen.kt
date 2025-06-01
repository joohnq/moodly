package com.joohnq.stress_level.ui.presentation.stress_stressors

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.stress_level.ui.presentation.add_stress_level.viewmodel.AddStressLevelContract
import com.joohnq.stress_level.ui.presentation.add_stress_level.viewmodel.AddStressLevelViewModel
import com.joohnq.stress_level.ui.presentation.stress_level.viewmodel.StressLevelContract
import com.joohnq.stress_level.ui.presentation.stress_level.viewmodel.StressLevelViewModel
import com.joohnq.stress_level.ui.presentation.stress_stressors.event.StressStressorsContract
import com.joohnq.stress_level.ui.resource.mapper.toDomain
import com.joohnq.ui.sharedViewModel
import kotlinx.coroutines.launch

@Composable
fun StressStressorsScreen(
    onGoBack: () -> Unit,
    onNavigateToStressLevel: () -> Unit,
) {
    val stressLevelViewModel: StressLevelViewModel = sharedViewModel()
    val addStressLevelViewModel: AddStressLevelViewModel = sharedViewModel()
    val snackBarState = rememberSnackBarState()
    val scope = rememberCoroutineScope()
    val state by addStressLevelViewModel.state.collectAsState()

    fun onError(error: String) {
        scope.launch { snackBarState.showSnackbar(error) }
    }

    fun onEvent(event: StressStressorsContract.Event) =
        when (event) {
            is StressStressorsContract.Event.GoBack -> onGoBack()
            is StressStressorsContract.Event.Continue -> {
                stressLevelViewModel.onIntent(
                    StressLevelContract.Intent.Add(
                        state.record.toDomain()
                    )
                )
            }
        }

    LaunchedEffect(stressLevelViewModel) {
        stressLevelViewModel.sideEffect.collect { event ->
            when (event) {
                is StressLevelContract.SideEffect.StressLevelAdded -> {
                    onNavigateToStressLevel()
                    stressLevelViewModel.onIntent(StressLevelContract.Intent.GetAll)
                }

                is StressLevelContract.SideEffect.ShowError -> onError(event.error.message.toString())
                else -> Unit
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            addStressLevelViewModel.onIntent(AddStressLevelContract.Intent.ResetState)
        }
    }

    StressStressorsUI(
        snackBarState = snackBarState,
        state = state,
        onEvent = ::onEvent,
        onIntent = addStressLevelViewModel::onIntent,
    )
}
