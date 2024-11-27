package com.joohnq.moodapp.ui.presentation.stress_stressors

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.moodapp.domain.StressLevelRecord
import com.joohnq.moodapp.domain.Stressor
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.ui.CustomScreen
import com.joohnq.moodapp.ui.presentation.add_stress_level.AddStressLevelIntent
import com.joohnq.moodapp.ui.presentation.add_stress_level.AddStressLevelViewModel
import com.joohnq.moodapp.ui.presentation.stress_level.StressLevelScreen
import com.joohnq.moodapp.ui.presentation.stress_stressors.event.StressStressorsEvent
import com.joohnq.moodapp.ui.presentation.stress_stressors.state.StressStressorsState
import com.joohnq.moodapp.ui.state.UiState.Companion.fold
import com.joohnq.moodapp.viewmodel.StressLevelIntent
import com.joohnq.moodapp.viewmodel.StressLevelViewModel
import kotlinx.coroutines.launch

class StressStressorsScreen : CustomScreen<StressStressorsState>() {
    @Composable
    override fun Screen(): StressStressorsState {
        val stressLevelViewModel: StressLevelViewModel = sharedViewModel()
        val addStressLevelViewModel: AddStressLevelViewModel = sharedViewModel()
        val snackBarState = remember { SnackbarHostState() }
        val scope = rememberCoroutineScope()
        val stressLevelState by stressLevelViewModel.stressLevelState.collectAsState()
        val addStressLevelState by addStressLevelViewModel.addStressLevelState.collectAsState()

        fun onEvent(event: StressStressorsEvent) =
            when (event) {
                is StressStressorsEvent.OnGoBack -> onGoBack()
                is StressStressorsEvent.OnContinue -> {
                    try {
                        addStressLevelViewModel.onAction(
                            AddStressLevelIntent.UpdateAddingOtherValue(
                                ""
                            )
                        )

                        if (addStressLevelState.stressors.any { it::class == Stressor.Other::class } && addStressLevelState.otherValue.isEmpty()) throw Exception(
                            "Please type your other stressor"
                        )

                        addStressLevelViewModel.onAction(
                            AddStressLevelIntent.UpdateAddingStressors(
                                Stressor.Other(
                                    addStressLevelState.otherValue
                                )
                            )
                        )

                        stressLevelViewModel.onAction(
                            StressLevelIntent.AddStressLevelRecord(
                                StressLevelRecord.Builder()
                                    .setStressLevel(addStressLevelState.stressLevel)
                                    .setStressors(addStressLevelState.stressors)
                                    .build()
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
