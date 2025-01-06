package com.joohnq.stress_level.ui.presentation.add_stress_level

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.core.ui.mapper.fold
import com.joohnq.shared_resources.CustomScreen
import com.joohnq.shared_resources.sharedViewModel
import com.joohnq.stress_level.domain.entity.StressLevel
import com.joohnq.stress_level.domain.entity.StressLevelRecord
import com.joohnq.stress_level.domain.entity.Stressor
import com.joohnq.stress_level.ui.presentation.add_stress_level.event.AddStressLevelEvent
import com.joohnq.stress_level.ui.presentation.add_stress_level.state.AddStressLevelState
import com.joohnq.stress_level.ui.presentation.add_stress_level.viewmodel.AddStressLevelViewModel
import com.joohnq.stress_level.ui.resource.StressLevelResource
import com.joohnq.stress_level.ui.viewmodel.StressLevelIntent
import com.joohnq.stress_level.ui.viewmodel.StressLevelViewModel
import kotlinx.coroutines.launch

class AddStressLevelScreen(
    private val onNavigateToStressStressors: () -> Unit,
    private val onGoBack: () -> Unit,
) : CustomScreen<AddStressLevelState>() {
    @Composable
    override fun Screen(): AddStressLevelState {
        val stressLevelViewModel: StressLevelViewModel = sharedViewModel()
        val addStressLevelViewModel: AddStressLevelViewModel = sharedViewModel()
        val snackBarState = remember { SnackbarHostState() }
        val scope = rememberCoroutineScope()
        val stressLevelState by stressLevelViewModel.state.collectAsState()
        val addStressLevelState by addStressLevelViewModel.state.collectAsState()

        fun onError(error: String) = scope.launch { snackBarState.showSnackbar(error) }

        fun onEvent(event: AddStressLevelEvent) {
            when (event) {
                AddStressLevelEvent.GoBack -> onGoBack()
                AddStressLevelEvent.Continue -> {
                    if (addStressLevelState.stressLevel != StressLevelResource.One) {
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

        LaunchedEffect(stressLevelState.adding) {
            stressLevelState.adding.fold(
                onError = ::onError,
                onSuccess = {
                    onEvent(AddStressLevelEvent.PopUpToStressLevelLevel)
                    stressLevelViewModel.onAction(StressLevelIntent.GetStressLevelRecords)
                    stressLevelViewModel.onAction(StressLevelIntent.ResetAddingStatus)
                },
            )
        }

        return AddStressLevelState(
            snackBarState = snackBarState,
            state = addStressLevelState,
            onAction = stressLevelViewModel::onAction,
            onEvent = ::onEvent,
            onAddAction = addStressLevelViewModel::onAction
        )
    }

    @Composable
    override fun UI(state: AddStressLevelState) = AddStressLevelScreenUI(state)
}
