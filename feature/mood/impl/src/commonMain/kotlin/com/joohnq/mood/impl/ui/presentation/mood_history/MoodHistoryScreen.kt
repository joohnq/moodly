package com.joohnq.mood.impl.ui.presentation.mood_history

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.ui.sharedViewModel
import com.joohnq.mood.impl.ui.presentation.mood_history.event.MoodHistoryEvent
import com.joohnq.mood.impl.ui.viewmodel.MoodViewModel

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

    MoodHistoryContent(
        records = statsState.records,
        onEvent = ::onEvent
    )
}
