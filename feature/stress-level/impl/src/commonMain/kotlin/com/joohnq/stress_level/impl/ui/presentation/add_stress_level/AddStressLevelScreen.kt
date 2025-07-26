package com.joohnq.stress_level.impl.ui.presentation.add_stress_level

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.stress_level.api.entity.StressLevel
import com.joohnq.stress_level.api.entity.StressLevelRecord
import com.joohnq.stress_level.impl.ui.presentation.stress_level.StressLevelContract
import com.joohnq.stress_level.impl.ui.presentation.stress_level.StressLevelViewModel
import com.joohnq.stress_level.impl.ui.resource.StressLevelResource
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

    fun onError(error: String) = scope.launch { snackBarState.showSnackbar(error) }

    fun onEvent(event: AddStressLevelContract.Event) {
        when (event) {
            AddStressLevelContract.Event.OnGoBack -> onGoBack()
            AddStressLevelContract.Event.OnContinue -> {
                if (state.record.stressLevel != StressLevelResource.One) {
                    onNavigateToStressStressors()
                    return
                }

                stressLevelViewModel.onIntent(
                    StressLevelContract.Intent.Add(
                        StressLevelRecord(
                            stressLevel = StressLevel.One
                        )
                    )
                )
            }

            AddStressLevelContract.Event.OnPopUpToStressLevelLevel -> onGoBack()
        }
    }

    LaunchedEffect(stressLevelViewModel) {
        stressLevelViewModel.sideEffect.collect { event ->
            when (event) {
                is StressLevelContract.SideEffect.Added -> {
                    onEvent(AddStressLevelContract.Event.OnPopUpToStressLevelLevel)
                    stressLevelViewModel.onIntent(StressLevelContract.Intent.GetAll)
                }

                is StressLevelContract.SideEffect.ShowError -> onError(event.error)
                else -> Unit
            }
        }
    }

    AddStressLevelScreenContent(
        snackBarState = snackBarState,
        state = state,
        onEvent = ::onEvent,
        onAction = addStressLevelViewModel::onIntent
    )
}
