package com.joohnq.stress_level.ui.presentation.stress_level

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.stress_level.ui.presentation.stress_level.event.StressLevelEvent
import com.joohnq.stress_level.ui.viewmodel.StressLevelViewModel

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
        onEvent = ::onEvent
    )
}
