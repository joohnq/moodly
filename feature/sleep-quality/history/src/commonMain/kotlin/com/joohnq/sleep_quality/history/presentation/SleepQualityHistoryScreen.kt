package com.joohnq.sleep_quality.history.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.joohnq.ui.sharedViewModel

@Composable
fun SleepQualityHistoryScreen(
    onGoBack: () -> Unit,
    viewModel: SleepQualityHistoryViewModel = sharedViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    fun onEvent(event: SleepQualityHistoryContract.Event) {
        when (event) {
            SleepQualityHistoryContract.Event.GoBack -> onGoBack()
        }
    }

    SleepQualityHistoryContent(
        state = state,
        onEvent = ::onEvent,
        onIntent = viewModel::onIntent
    )
}
