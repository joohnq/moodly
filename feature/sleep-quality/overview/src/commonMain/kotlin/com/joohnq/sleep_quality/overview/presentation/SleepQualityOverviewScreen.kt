package com.joohnq.sleep_quality.overview.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.joohnq.ui.sharedViewModel

@Composable
fun SleepQualityOverviewScreen(
    onNavigateAddSleepQuality: () -> Unit,
    onGoBack: () -> Unit,
    onNavigateToSleepHistory: () -> Unit,
    viewModel: SleepQualityOverviewViewModel = sharedViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    fun onEvent(event: SleepQualityOverviewContract.Event) {
        when (event) {
            SleepQualityOverviewContract.Event.GoBack -> onGoBack()
            SleepQualityOverviewContract.Event.NavigateToAddSleepQuality -> onNavigateAddSleepQuality()
            SleepQualityOverviewContract.Event.NavigateToSleepQualityHistory -> onNavigateToSleepHistory()
        }
    }

    SleepQualityOverviewContent(
        state = state,
        onEvent = ::onEvent,
        onIntent = viewModel::onIntent
    )
}
