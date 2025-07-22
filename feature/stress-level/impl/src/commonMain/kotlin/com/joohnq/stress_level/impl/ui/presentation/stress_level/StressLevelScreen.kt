package com.joohnq.stress_level.impl.ui.presentation.stress_level

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.stress_level.impl.ui.presentation.stress_level.event.StressLevelEvent
import com.joohnq.stress_level.impl.ui.viewmodel.StressLevelViewModel
import com.joohnq.ui.sharedViewModel

@Composable
fun StressLevelScreen(
    onNavigateAddStressLevel: () -> Unit,
    onGoBack: () -> Unit,
) {
    val stressLevelViewModel: StressLevelViewModel = sharedViewModel()
    val state by stressLevelViewModel.state.collectAsState()

    fun onEvent(event: StressLevelEvent) =
        when (event) {
            is StressLevelEvent.onAddStressLevel -> onNavigateAddStressLevel()
            is StressLevelEvent.OnGoBack -> onGoBack()
        }

    return StressLevelUI(
        state = state,
        onEvent = ::onEvent,
        onAction = stressLevelViewModel::onAction
    )
}
