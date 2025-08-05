package com.joohnq.stress_level.overview.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.ui.sharedViewModel

@Composable
fun StressLevelOverviewScreen(
    onNavigateAddStressLevel: () -> Unit,
    onGoBack: () -> Unit,
    viewModel: StressLevelOverviewViewModel = sharedViewModel(),
) {
    val state by viewModel.state.collectAsState()

    fun onEvent(event: StressLevelOverviewContract.Event) =
        when (event) {
            is StressLevelOverviewContract.Event.navigateToAddStressLevel -> onNavigateAddStressLevel()
            is StressLevelOverviewContract.Event.GoBack -> onGoBack()
        }

    StressLevelOverviewContent(
        state = state,
        onEvent = ::onEvent,
        onIntent = viewModel::onIntent
    )
}