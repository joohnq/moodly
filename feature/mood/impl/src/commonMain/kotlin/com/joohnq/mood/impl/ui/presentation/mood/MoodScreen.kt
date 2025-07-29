package com.joohnq.mood.impl.ui.presentation.mood

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.ui.sharedViewModel

@Composable
fun MoodScreen(
    onGoBack: () -> Unit,
    onNavigateToAddMood: () -> Unit,
    onNavigateToMoodHistory: () -> Unit,
    viewModel: MoodViewModel = sharedViewModel()
) {
    val state by viewModel.state.collectAsState()

    fun onEvent(event: MoodContract.Event) =
        when (event) {
            is MoodContract.Event.OnGoBack -> onGoBack()
            is MoodContract.Event.OnAddMood -> onNavigateToAddMood()
            MoodContract.Event.OnNavigateToMoodHistory -> onNavigateToMoodHistory()
        }

    MoodContent(
        records = state.records,
        onEvent = ::onEvent,
        onAction = viewModel::onIntent
    )
}