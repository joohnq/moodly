package com.joohnq.stress_level.ui.presentation.stress_history

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.stress_level.ui.presentation.stress_history.event.StressHistoryEvent
import com.joohnq.stress_level.ui.viewmodel.StressLevelViewModel

@Composable
fun StressHistoryScreen(
    onGoBack: () -> Unit,
    onAddStressLevel: () -> Unit,
    onNavigateStressLevel: () -> Unit,
) {
    val stressLevelViewModel: StressLevelViewModel = sharedViewModel()
    val state by stressLevelViewModel.state.collectAsState()

    fun onEvent(event: StressHistoryEvent) {
        when (event) {
            StressHistoryEvent.OnGoBack -> onGoBack()
            is StressHistoryEvent.OnNavigateToStressLevel -> onNavigateStressLevel()
            StressHistoryEvent.OnAddStressLevel -> onAddStressLevel()
        }
    }

    StressHistoryUI(
        state = state,
        onEvent = ::onEvent
    )
}