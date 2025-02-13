package com.joohnq.mood.ui.presentation.mood

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.mood.ui.presentation.mood.event.MoodEvent
import com.joohnq.mood.ui.viewmodel.MoodViewModel
import com.joohnq.ui.sharedViewModel

@Composable
fun MoodScreen(
    onGoBack: () -> Unit,
    onNavigateToAddMood: () -> Unit,
    onNavigateToMoodHistory: () -> Unit,
) {
    val moodViewModel: MoodViewModel = sharedViewModel()
    val statsState by moodViewModel.state.collectAsState()

    fun onEvent(event: MoodEvent) =
        when (event) {
            is MoodEvent.OnGoBack -> onGoBack()
            is MoodEvent.OnAddMood -> onNavigateToAddMood()
            MoodEvent.OnNavigateToMoodHistory -> onNavigateToMoodHistory()
        }

    MoodUI(
        records = statsState.records,
        onEvent = ::onEvent
    )
}
