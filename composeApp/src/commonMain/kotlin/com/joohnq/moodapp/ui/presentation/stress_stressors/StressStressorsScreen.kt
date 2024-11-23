package com.joohnq.moodapp.ui.presentation.stress_stressors

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.moodapp.domain.Stressor
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.ui.CustomScreen
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
        val snackBarState = remember { SnackbarHostState() }
        val scope = rememberCoroutineScope()
        val stressLevelState by stressLevelViewModel.stressLevelState.collectAsState()

        fun onEvent(event: StressStressorsEvent) =
            when (event) {
                is StressStressorsEvent.OnGoBack -> onGoBack()
                is StressStressorsEvent.OnAddStressor ->
                    stressLevelViewModel.onAction(
                        StressLevelIntent.UpdateAddingStressors(
                            event.stressor
                        )
                    )

                is StressStressorsEvent.OnContinue -> {
                    if (stressLevelState.adding.stressors.any { it::class == Stressor.Other::class } && stressLevelState.adding.otherValue.isEmpty()) {
                        stressLevelViewModel.onAction(
                            StressLevelIntent.UpdateAddingOtherValueError(
                                "Please type your other stressor"
                            )
                        )
                    }

                    stressLevelViewModel.onAction(StressLevelIntent.UpdateAddingOtherValue(""))
                    stressLevelViewModel.onAction(StressLevelIntent.AddStressLevelRecord())
                }

                StressStressorsEvent.OnGoBackToStressLevel -> {}
            }

        LaunchedEffect(stressLevelState.adding.stressors) {
            if (stressLevelState.adding.stressors.any { it::class == Stressor.Other::class }) {
                stressLevelViewModel.onAction(StressLevelIntent.UpdateAddingOtherValue(""))
            }
        }

        LaunchedEffect(stressLevelState.adding.status) {
            stressLevelState.adding.status.fold(
                onError = { error -> scope.launch { snackBarState.showSnackbar(error) } },
                onSuccess = {
                    onEvent(StressStressorsEvent.OnGoBackToStressLevel)
                    stressLevelViewModel.onAction(StressLevelIntent.ResetAdding)
                },
            )
        }

        return StressStressorsState(
            snackBarState = snackBarState,
            otherValueError = stressLevelState.adding.otherValueError,
            selectedStressors = stressLevelState.adding.stressors,
            otherValue = stressLevelState.adding.otherValue,
            onAction = stressLevelViewModel::onAction,
            onEvent = ::onEvent
        )
    }

    @Composable
    override fun UI(state: StressStressorsState) = StressStressorsUI(state)
}
