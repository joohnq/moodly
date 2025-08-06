package com.joohnq.stress_level.history.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.ui.sharedViewModel

@Composable
fun StressLevelHistoryScreen(
    onGoBack: () -> Unit,
    onAddStressLevel: () -> Unit,
    onNavigateStressLevel: () -> Unit,
    viewModel: StressLevelHistoryViewModel = sharedViewModel(),
) {
    val state by viewModel.state.collectAsState()

    fun onEvent(event: StressLevelHistoryContract.Event) {
        when (event) {
            StressLevelHistoryContract.Event.GoBack -> onGoBack()
        }
    }

    StressLevelHistoryContent(
        state = state,
        onEvent = ::onEvent
    )
}
