package com.joohnq.mood.ui.presentation.mood_history

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.mood.ui.presentation.mood_history.event.MoodHistoryEvent
import com.joohnq.mood.ui.viewmodel.MoodViewModel

@Composable
fun MoodHistoryScreen(
    onGoBack: () -> Unit,
) {
    val moodViewModel: MoodViewModel = sharedViewModel()
    val statsState by moodViewModel.state.collectAsState()

    fun onEvent(event: MoodHistoryEvent) =
        when (event) {
            is MoodHistoryEvent.OnGoBack -> onGoBack()
        }

    MoodHistoryUI(
        records = statsState.records,
        onEvent = ::onEvent
    )
}
