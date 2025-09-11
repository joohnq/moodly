package com.joohnq.stress_level.history.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.joohnq.ui.sharedViewModel

@Composable
fun StressLevelHistoryScreen(
    onGoBack: () -> Unit,
    viewModel: StressLevelHistoryViewModel = sharedViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

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
