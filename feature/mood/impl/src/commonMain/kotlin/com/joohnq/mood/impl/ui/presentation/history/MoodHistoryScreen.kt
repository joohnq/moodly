package com.joohnq.mood.impl.ui.presentation.history

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.ui.sharedViewModel

@Composable
fun MoodHistoryScreen(
    onGoBack: () -> Unit,
    viewModel: MoodHistoryViewModel = sharedViewModel(),
) {
    val state by viewModel.state.collectAsState()

    fun onEvent(event: MoodHistoryContract.Event) =
        when (event) {
            is MoodHistoryContract.Event.OnGoBack -> onGoBack()
        }

    MoodHistoryContent(
        records = state.records,
        onEvent = ::onEvent
    )
}
