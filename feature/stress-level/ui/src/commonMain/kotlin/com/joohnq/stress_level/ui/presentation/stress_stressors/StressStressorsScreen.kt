package com.joohnq.stress_level.ui.presentation.stress_stressors

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.shared.ui.CustomScreen
import com.joohnq.shared.ui.sharedViewModel
import com.joohnq.shared.ui.state.UiState.Companion.fold
import com.joohnq.stress_level.domain.entity.StressLevelRecord
import com.joohnq.stress_level.domain.entity.Stressor
import com.joohnq.stress_level.ui.presentation.add_stress_level.viewmodel.AddStressLevelIntent
import com.joohnq.stress_level.ui.presentation.add_stress_level.viewmodel.AddStressLevelViewModel
import com.joohnq.stress_level.ui.presentation.stress_level.StressLevelScreen
import com.joohnq.stress_level.ui.presentation.stress_stressors.event.StressStressorsEvent
import com.joohnq.stress_level.ui.presentation.stress_stressors.state.StressStressorsState
import com.joohnq.stress_level.ui.viewmodel.StressLevelIntent
import com.joohnq.stress_level.ui.viewmodel.StressLevelViewModel
import kotlinx.coroutines.launch

class StressStressorsScreen : CustomScreen<StressStressorsState>() {
    @Composable
    override fun Screen(): StressStressorsState {
        val stressLevelViewModel: StressLevelViewModel = sharedViewModel()
        val addStressLevelViewModel: AddStressLevelViewModel = sharedViewModel()
        val snackBarState = remember { SnackbarHostState() }
        val scope = rememberCoroutineScope()
        val stressLevelState by stressLevelViewModel.stressLevelState.collectAsState()
        val addStressLevelState by addStressLevelViewModel.state.collectAsState()

        fun onEvent(event: StressStressorsEvent) =
            when (event) {
                is StressStressorsEvent.OnGoBack -> onGoBack()
                is StressStressorsEvent.OnContinue -> {
                    try {
                        if (addStressLevelState.stressors.any { it::class == Stressor.Other::class } && addStressLevelState.otherValue.isEmpty()) throw Exception(
                            "Please type your other stressor"
                        )

                        stressLevelViewModel.onAction(
                            StressLevelIntent.AddStressLevelRecord(
                                StressLevelRecord.init().copy(
                                    stressLevel = addStressLevelState.stressLevel,
                                    stressors = addStressLevelState.stressors
                                        .filterNot { it is Stressor.Other }
                                        .plus(
                                            Stressor.Other(addStressLevelState.otherValue)
                                        )
                                )
                            )
                        )
                    } catch (e: Exception) {
                        addStressLevelViewModel.onAction(
                            AddStressLevelIntent.UpdateAddingOtherValueError(
                                "Please type your other stressor"
                            )
                        )
                    }
                }
            }

        LaunchedEffect(addStressLevelState.stressors) {
            if (addStressLevelState.stressors.any { it::class == Stressor.Other::class }) {
                addStressLevelViewModel.onAction(AddStressLevelIntent.UpdateAddingOtherValue(""))
            }
        }

        LaunchedEffect(stressLevelState.adding) {
            stressLevelState.adding.fold(
                onError = { error -> scope.launch { snackBarState.showSnackbar(error) } },
                onSuccess = {
                    onGoBack(StressLevelScreen())
                    stressLevelViewModel.onAction(StressLevelIntent.ResetAddingStatus)
                    stressLevelViewModel.onAction(StressLevelIntent.GetStressLevelRecords)
                },
            )
        }

        DisposableEffect(Unit) {
            onDispose {
                addStressLevelViewModel.onAction(AddStressLevelIntent.ResetState)
            }
        }

        return StressStressorsState(
            snackBarState = snackBarState,
            addStressLevelViewModelState = addStressLevelState,
            onEvent = ::onEvent,
            onAddAction = addStressLevelViewModel::onAction,
        )
    }

    @Composable
    override fun UI(state: StressStressorsState) = StressStressorsUI(state)
}
