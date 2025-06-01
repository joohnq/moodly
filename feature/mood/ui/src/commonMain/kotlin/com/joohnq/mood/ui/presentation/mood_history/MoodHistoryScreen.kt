package com.joohnq.mood.ui.presentation.mood_history

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.ui.sharedViewModel
import com.joohnq.mood.ui.presentation.mood.viewmodel.MoodViewModel

@Composable
fun MoodHistoryScreen(
    onGoBack: () -> Unit,
) {
    val moodViewModel: MoodViewModel = sharedViewModel()
    val statsState by moodViewModel.state.collectAsState()

    fun onEvent(event: MoodHistoryContract.Event) =
        when (event) {
            is MoodHistoryContract.Event.GoBack -> onGoBack()
        }

    MoodHistoryUI(
        records = statsState.records,
        onEvent = ::onEvent
    )
}
