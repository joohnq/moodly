package com.joohnq.stress_level.ui.presentation.stress_level

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.stress_level.ui.presentation.stress_level.viewmodel.StressLevelContract
import com.joohnq.stress_level.ui.presentation.stress_level.viewmodel.StressLevelViewModel
import com.joohnq.ui.sharedViewModel

@Composable
fun StressLevelScreen(
    onNavigateAddStressLevel: () -> Unit,
    onGoBack: () -> Unit,
) {
    val stressLevelViewModel: StressLevelViewModel = sharedViewModel()
    val state by stressLevelViewModel.state.collectAsState()

    fun onEvent(event: StressLevelContract.Event) =
        when (event) {
            is StressLevelContract.Event.AddStressLevel -> onNavigateAddStressLevel()
            is StressLevelContract.Event.GoBack -> onGoBack()
        }

    return StressLevelUI(
        state = state,
        onEvent = ::onEvent,
        onIntent = stressLevelViewModel::onIntent
    )
}
