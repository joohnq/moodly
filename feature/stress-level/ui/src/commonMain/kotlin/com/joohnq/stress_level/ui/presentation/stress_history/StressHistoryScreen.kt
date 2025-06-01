package com.joohnq.stress_level.ui.presentation.stress_history

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.stress_level.ui.presentation.stress_level.viewmodel.StressLevelViewModel
import com.joohnq.ui.sharedViewModel

@Composable
fun StressHistoryScreen(
    onGoBack: () -> Unit,
    onAddStressLevel: () -> Unit,
    onNavigateStressLevel: () -> Unit,
) {
    val stressLevelViewModel: StressLevelViewModel = sharedViewModel()
    val state by stressLevelViewModel.state.collectAsState()

    fun onEvent(event: StressHistoryContract.Event) {
        when (event) {
            StressHistoryContract.Event.GoBack -> onGoBack()
            is StressHistoryContract.Event.NavigateToStressLevel -> onNavigateStressLevel()
            StressHistoryContract.Event.AddStressLevel -> onAddStressLevel()
        }
    }

    StressHistoryUI(
        state = state,
        onEvent = ::onEvent
    )
}