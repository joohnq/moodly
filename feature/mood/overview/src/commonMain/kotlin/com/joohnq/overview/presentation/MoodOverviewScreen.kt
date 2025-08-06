package com.joohnq.overview.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.ui.sharedViewModel

@Composable
fun MoodOverviewScreen(
    onGoBack: () -> Unit,
    onNavigateToAddMood: () -> Unit,
    onNavigateToMoodHistory: () -> Unit,
    viewModel: MoodOverviewViewModel = sharedViewModel(),
) {
    val state by viewModel.state.collectAsState()

    fun onEvent(event: MoodOverviewContract.Event) =
        when (event) {
            is MoodOverviewContract.Event.GoBack -> onGoBack()
            is MoodOverviewContract.Event.NavigateToAddMood -> onNavigateToAddMood()
            MoodOverviewContract.Event.NavigateToMoodHistory -> onNavigateToMoodHistory()
        }

    MoodOverviewContent(
        records = state.records,
        onEvent = ::onEvent,
        onIntent = viewModel::onIntent
    )
}
