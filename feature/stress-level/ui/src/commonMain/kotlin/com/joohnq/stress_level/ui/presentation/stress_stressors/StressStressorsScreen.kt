package com.joohnq.stress_level.ui.presentation.stress_stressors

import androidx.compose.runtime.*
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.stress_level.ui.mapper.toDomain
import com.joohnq.stress_level.ui.presentation.add_stress_level.viewmodel.AddStressLevelIntent
import com.joohnq.stress_level.ui.presentation.add_stress_level.viewmodel.AddStressLevelViewModel
import com.joohnq.stress_level.ui.presentation.stress_stressors.event.StressStressorsEvent
import com.joohnq.stress_level.ui.viewmodel.StressLevelIntent
import com.joohnq.stress_level.ui.viewmodel.StressLevelSideEffect
import com.joohnq.stress_level.ui.viewmodel.StressLevelViewModel
import kotlinx.coroutines.launch

@Composable
fun StressStressorsScreen(
    onGoBack: () -> Unit,
    onNavigateBackToStressLevel: () -> Unit,
) {
    val stressLevelViewModel: StressLevelViewModel = sharedViewModel()
    val addStressLevelViewModel: AddStressLevelViewModel = sharedViewModel()
    val snackBarState = rememberSnackBarState()
    val scope = rememberCoroutineScope()
    val state by addStressLevelViewModel.state.collectAsState()

    fun onError(error: Throwable) {
        scope.launch { snackBarState.showSnackbar(error.message.toString()) }
    }

    fun onEvent(event: StressStressorsEvent) =
        when (event) {
            is StressStressorsEvent.GoBack -> onGoBack()
            is StressStressorsEvent.Continue -> {
                stressLevelViewModel.onAction(
                    StressLevelIntent.AddStressLevelRecord(
                        state.record.toDomain()
                    )
                )
            }
        }

    LaunchedEffect(stressLevelViewModel) {
        stressLevelViewModel.sideEffect.collect { event ->
            when (event) {
                is StressLevelSideEffect.StressLevelAdded -> {
                    onNavigateBackToStressLevel()
                    stressLevelViewModel.onAction(StressLevelIntent.GetStressLevelRecords)
                }

                is StressLevelSideEffect.ShowError -> onError(event.error)
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
