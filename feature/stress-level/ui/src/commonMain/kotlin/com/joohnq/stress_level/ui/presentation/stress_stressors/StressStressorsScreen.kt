package com.joohnq.stress_level.ui.presentation.stress_stressors

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.stress_level.ui.mapper.toDomain
import com.joohnq.stress_level.ui.presentation.add_stress_level.viewmodel.AddStressLevelIntent
import com.joohnq.stress_level.ui.presentation.add_stress_level.viewmodel.AddStressLevelViewModel
import com.joohnq.stress_level.ui.presentation.stress_stressors.event.StressStressorsEvent
import com.joohnq.stress_level.ui.viewmodel.StressLevelIntent
import com.joohnq.stress_level.ui.viewmodel.StressLevelSideEffect
import com.joohnq.stress_level.ui.viewmodel.StressLevelViewModel
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

    fun onEvent(event: StressStressorsEvent) =
        when (event) {
            is StressStressorsEvent.GoBack -> onGoBack()
            is StressStressorsEvent.Continue -> {
                stressLevelViewModel.onAction(
                    StressLevelIntent.Add(
                        state.record.toDomain()
                    )
                )
            }
        }

    LaunchedEffect(stressLevelViewModel) {
        stressLevelViewModel.sideEffect.collect { event ->
            when (event) {
                is StressLevelSideEffect.StressLevelAdded -> {
                    onNavigateToStressLevel()
                    stressLevelViewModel.onAction(StressLevelIntent.GetAll)
                }

                is StressLevelSideEffect.ShowError -> onError(event.error)
                else -> Unit
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            addStressLevelViewModel.onAction(AddStressLevelIntent.ResetState)
        }
    }

    StressStressorsUI(
        snackBarState = snackBarState,
        state = state,
        onEvent = ::onEvent,
        onAddAction = addStressLevelViewModel::onAction,
    )
}
