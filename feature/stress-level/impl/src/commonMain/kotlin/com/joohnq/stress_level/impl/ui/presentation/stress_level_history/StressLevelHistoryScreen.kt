package com.joohnq.stress_level.impl.ui.presentation.stress_level_history

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.stress_level.impl.ui.presentation.stress_level.StressLevelViewModel
import com.joohnq.ui.sharedViewModel

@Composable
fun StressLevelHistoryScreen(
    onGoBack: () -> Unit,
    onAddStressLevel: () -> Unit,
    onNavigateStressLevel: () -> Unit,
) {
    val stressLevelViewModel: StressLevelViewModel = sharedViewModel()
    val state by stressLevelViewModel.state.collectAsState()

    fun onEvent(event: StressLevelHistoryContract.Event) {
        when (event) {
            StressLevelHistoryContract.Event.OnGoBack -> onGoBack()
            is StressLevelHistoryContract.Event.OnNavigateToStressLevel -> onNavigateStressLevel()
            StressLevelHistoryContract.Event.OnAddStressLevel -> onAddStressLevel()
        }
    }

    StressLevelHistoryContent(
        state = state,
        onEvent = ::onEvent
    )
}
