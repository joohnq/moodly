package com.joohnq.mood.impl.ui.presentation.mood_history

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.mood.impl.ui.presentation.mood.MoodViewModel
import com.joohnq.ui.sharedViewModel

@Composable
fun MoodHistoryScreen(
    onGoBack: () -> Unit,
    moodViewModel: MoodViewModel = sharedViewModel()
) {
    val state by moodViewModel.state.collectAsState()

    fun onEvent(event: MoodHistoryContract.Event) =
        when (event) {
            is MoodHistoryContract.Event.OnGoBack -> onGoBack()
        }

    MoodHistoryContent(
        records = state.records,
        onEvent = ::onEvent
    )
}