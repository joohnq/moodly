package com.joohnq.stress_level.ui.presentation.add_stress_level

import androidx.compose.runtime.*
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.stress_level.domain.entity.StressLevel
import com.joohnq.stress_level.domain.entity.StressLevelRecord
import com.joohnq.stress_level.domain.entity.Stressor
import com.joohnq.stress_level.ui.presentation.add_stress_level.event.AddStressLevelEvent
import com.joohnq.stress_level.ui.presentation.add_stress_level.viewmodel.AddStressLevelViewModel
import com.joohnq.stress_level.ui.resource.StressLevelResource
import com.joohnq.stress_level.ui.viewmodel.StressLevelIntent
import com.joohnq.stress_level.ui.viewmodel.StressLevelSideEffect
import com.joohnq.stress_level.ui.viewmodel.StressLevelViewModel
import kotlinx.coroutines.launch

@Composable
fun AddStressLevelScreen(
    onNavigateToStressStressors: () -> Unit,
    onGoBack: () -> Unit,
) {
    val stressLevelViewModel: StressLevelViewModel = sharedViewModel()
    val addStressLevelViewModel: AddStressLevelViewModel = sharedViewModel()
    val snackBarState = rememberSnackBarState()
    val scope = rememberCoroutineScope()
    val state by addStressLevelViewModel.state.collectAsState()

    fun onError(error: Throwable) =
        scope.launch { snackBarState.showSnackbar(error.message.toString()) }

    fun onEvent(event: AddStressLevelEvent) {
        when (event) {
            AddStressLevelEvent.GoBack -> onGoBack()
            AddStressLevelEvent.Continue -> {
                if (state.stressLevel != StressLevelResource.One) {
                    onNavigateToStressStressors()
                    return
                }

                stressLevelViewModel.onAction(
                    StressLevelIntent.AddStressLevelRecord(
                        StressLevelRecord(
                            stressLevel = StressLevel.One,
                            stressors = listOf(Stressor.InPeace)
                        )
                    )
                )
            }

            AddStressLevelEvent.PopUpToStressLevelLevel -> onGoBack()
        }
    }

    LaunchedEffect(stressLevelViewModel) {
        stressLevelViewModel.sideEffect.collect { event ->
            when (event) {
                is StressLevelSideEffect.StressLevelAdded -> {
                    onEvent(AddStressLevelEvent.PopUpToStressLevelLevel)
                    stressLevelViewModel.onAction(StressLevelIntent.GetStressLevelRecords)
                }

                is StressLevelSideEffect.ShowError -> onError(event.error)
            }
        }
    }

    AddStressLevelScreenUI(
        snackBarState = snackBarState,
        state = state,
        onEvent = ::onEvent,
        onAction = addStressLevelViewModel::onAction
    )
}
