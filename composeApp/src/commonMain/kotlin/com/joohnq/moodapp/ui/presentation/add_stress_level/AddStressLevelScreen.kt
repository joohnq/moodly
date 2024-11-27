package com.joohnq.moodapp.ui.presentation.add_stress_level

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.moodapp.domain.StressLevel
import com.joohnq.moodapp.domain.StressLevelRecord
import com.joohnq.moodapp.domain.Stressor
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.ui.CustomScreen
import com.joohnq.moodapp.ui.presentation.add_stress_level.event.AddStressLevelEvent
import com.joohnq.moodapp.ui.presentation.add_stress_level.state.AddStressLevelState
import com.joohnq.moodapp.ui.presentation.stress_stressors.StressStressorsScreen
import com.joohnq.moodapp.ui.state.UiState.Companion.fold
import com.joohnq.moodapp.viewmodel.StressLevelIntent
import com.joohnq.moodapp.viewmodel.StressLevelViewModel
import kotlinx.coroutines.launch

class AddStressLevelScreen : CustomScreen<AddStressLevelState>() {
    @Composable
    override fun Screen(): AddStressLevelState {
        val stressLevelViewModel: StressLevelViewModel = sharedViewModel()
        val addStressLevelViewModel: AddStressLevelViewModel = sharedViewModel()
        val snackBarState = remember { SnackbarHostState() }
        val scope = rememberCoroutineScope()
        val stressLevelState by stressLevelViewModel.stressLevelState.collectAsState()
        val addStressLevelState by addStressLevelViewModel.addStressLevelState.collectAsState()

        LaunchedEffect(stressLevelState.adding) {
            stressLevelState.adding.fold(
                onError = { error -> scope.launch { snackBarState.showSnackbar(error) } },
                onSuccess = {
                    onGoBack()
                    stressLevelViewModel.onAction(StressLevelIntent.GetStressLevelRecords)
                    stressLevelViewModel.onAction(StressLevelIntent.ResetAddingStatus)
                },
            )
        }

        fun onEvent(event: AddStressLevelEvent) {
            when (event) {
                AddStressLevelEvent.OnGoBack -> onGoBack()
                AddStressLevelEvent.OnContinue -> {
                    if (addStressLevelState.stressLevel != StressLevel.One) {
                        onNavigate(StressStressorsScreen())
                    } else {
                        stressLevelViewModel.onAction(
                            StressLevelIntent.AddStressLevelRecord(
                                StressLevelRecord.Builder()
                                    .setStressLevel(StressLevel.One)
                                    .setStressors(
                                        listOf(Stressor.InPeace)
                                    )
                                    .build()
                            )
                        )
                    }
                }

                AddStressLevelEvent.OnPopUpToStressLevelLevel -> onGoBack()
            }
        }

        return AddStressLevelState(
            snackBarState = snackBarState,
            addStressLevelViewModelState = addStressLevelState,
            onAction = stressLevelViewModel::onAction,
            onEvent = ::onEvent,
            onAddAction = addStressLevelViewModel::onAction
        )
    }

    @Composable
    override fun UI(state: AddStressLevelState) = AddStressLevelScreenUI(state)
}
