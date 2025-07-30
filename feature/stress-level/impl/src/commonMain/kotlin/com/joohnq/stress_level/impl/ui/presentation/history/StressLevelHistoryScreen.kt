package com.joohnq.stress_level.impl.ui.presentation.history

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.stress_level.impl.ui.presentation.overview.StressLevelOverviewViewModel
import com.joohnq.ui.sharedViewModel

@Composable
fun StressLevelHistoryScreen(
    onGoBack: () -> Unit,
    onAddStressLevel: () -> Unit,
    onNavigateStressLevel: () -> Unit,
    viewModel: StressLevelOverviewViewModel = sharedViewModel(),
) {
    val state by viewModel.state.collectAsState()

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
