package com.joohnq.history.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.joohnq.ui.sharedViewModel

@Composable
fun MoodHistoryScreen(
    onGoBack: () -> Unit,
    viewModel: MoodHistoryViewModel = sharedViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    fun onEvent(event: MoodHistoryContract.Event) {
        when (event) {
            is MoodHistoryContract.Event.GoBack -> onGoBack()
        }
    }

    MoodHistoryContent(
        state = state,
        onEvent = ::onEvent
    )
}
