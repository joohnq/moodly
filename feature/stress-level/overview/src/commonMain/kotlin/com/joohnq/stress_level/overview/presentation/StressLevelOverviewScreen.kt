package com.joohnq.stress_level.overview.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.joohnq.ui.sharedViewModel

@Composable
fun StressLevelOverviewScreen(
    onNavigateAddStressLevel: () -> Unit,
    navigateToStressLevelHistory: () -> Unit,
    onGoBack: () -> Unit,
    viewModel: StressLevelOverviewViewModel = sharedViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    fun onEvent(event: StressLevelOverviewContract.Event) {
        when (event) {
            is StressLevelOverviewContract.Event.NavigateToAddStressLevel -> onNavigateAddStressLevel()
            is StressLevelOverviewContract.Event.GoBack -> onGoBack()
            StressLevelOverviewContract.Event.NavigateToStressLevelHistory -> navigateToStressLevelHistory()
        }
    }

    StressLevelOverviewContent(
        state = state,
        onEvent = ::onEvent,
        onIntent = viewModel::onIntent
    )
}
