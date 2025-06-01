package com.joohnq.stress_level.ui.presentation.add_stress_level

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.stress_level.domain.entity.StressLevel
import com.joohnq.stress_level.domain.entity.StressLevelRecord
import com.joohnq.stress_level.ui.presentation.add_stress_level.viewmodel.AddStressLevelContract
import com.joohnq.stress_level.ui.presentation.add_stress_level.viewmodel.AddStressLevelViewModel
import com.joohnq.stress_level.ui.resource.StressLevelResource
import com.joohnq.stress_level.ui.presentation.stress_level.viewmodel.StressLevelContract
import com.joohnq.stress_level.ui.presentation.stress_level.viewmodel.StressLevelViewModel
import com.joohnq.ui.sharedViewModel
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

    fun onError(error: String) =
        scope.launch { snackBarState.showSnackbar(error) }

    fun onEvent(event: AddStressLevelContract.Event) {
        when (event) {
            AddStressLevelContract.Event.GoBack -> onGoBack()
            AddStressLevelContract.Event.Continue -> {
                if (state.record.stressLevel != StressLevelResource.One) {
                    onNavigateToStressStressors()
                    return
                }

                stressLevelViewModel.onIntent(
                    StressLevelContract.Intent.Add(
                        StressLevelRecord(
                            stressLevel = StressLevel.One,
                        )
                    )
                )
            }

            AddStressLevelContract.Event.PopUpToStressLevelLevel -> onGoBack()
        }
    }

    LaunchedEffect(stressLevelViewModel) {
        stressLevelViewModel.sideEffect.collect { event ->
            when (event) {
                is StressLevelContract.SideEffect.StressLevelAdded -> {
                    onEvent(AddStressLevelContract.Event.PopUpToStressLevelLevel)
                    stressLevelViewModel.onIntent(StressLevelContract.Intent.GetAll)
                }

                is StressLevelContract.SideEffect.ShowError -> onError(event.error.message.toString())
                else -> Unit
            }
        }
    }

    AddStressLevelScreenUI(
        snackBarState = snackBarState,
        state = state,
        onEvent = ::onEvent,
        onIntent = addStressLevelViewModel::onIntent
    )
}
