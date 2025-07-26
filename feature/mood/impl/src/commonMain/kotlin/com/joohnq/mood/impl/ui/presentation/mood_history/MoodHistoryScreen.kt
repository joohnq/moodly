package com.joohnq.mood.impl.ui.presentation.mood_history

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.mood.impl.ui.presentation.mood.MoodViewModel
import com.joohnq.ui.sharedViewModel

@Composable
fun MoodHistoryScreen(
    onGoBack: () -> Unit
) {
    val moodViewModel: MoodViewModel = sharedViewModel()
    val statsState by moodViewModel.state.collectAsState()

    fun onEvent(event: MoodHistoryContract.Event) =
        when (event) {
            is MoodHistoryContract.Event.OnGoBack -> onGoBack()
        }

    MoodHistoryContent(
        records = statsState.records,
        onEvent = ::onEvent
    )
}
