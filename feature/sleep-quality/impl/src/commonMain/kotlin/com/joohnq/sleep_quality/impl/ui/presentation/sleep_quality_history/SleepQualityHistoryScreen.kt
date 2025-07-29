package com.joohnq.sleep_quality.impl.ui.presentation.sleep_quality_history

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.sleep_quality.impl.ui.presentation.sleep_quality.SleepQualityContract
import com.joohnq.sleep_quality.impl.ui.presentation.sleep_quality.SleepQualityViewModel
import com.joohnq.ui.sharedViewModel

@Composable
fun SleepQualityHistoryScreen(
    onGoBack: () -> Unit,
    viewModel: SleepQualityViewModel = sharedViewModel(),
) {
    val state by viewModel.state.collectAsState()

    fun onEvent(event: SleepQualityHistoryContract.Event) {
        when (event) {
            SleepQualityHistoryContract.Event.OnGoBack -> onGoBack()
            is SleepQualityHistoryContract.Event.OnDelete -> {
                viewModel.onIntent(
                    SleepQualityContract.Intent.Delete(
                        event.id
                    )
                )
            }
        }
    }

    SleepQualityHistoryContent(
        state = state,
        onEvent = ::onEvent
    )
}