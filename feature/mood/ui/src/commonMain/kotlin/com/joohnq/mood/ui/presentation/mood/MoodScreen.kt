package com.joohnq.mood.ui.presentation.mood

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.mood.ui.presentation.mood.viewmodel.MoodContract
import com.joohnq.mood.ui.presentation.mood.viewmodel.MoodViewModel
import com.joohnq.ui.sharedViewModel

@Composable
fun MoodScreen(
    onGoBack: () -> Unit,
    onNavigateToAddMood: () -> Unit,
    onNavigateToMoodHistory: () -> Unit,
) {
    val viewModel: MoodViewModel = sharedViewModel()
    val state by viewModel.state.collectAsState()

    fun onEvent(event: MoodContract.Event) =
        when (event) {
            is MoodContract.Event.GoBack -> onGoBack()
            is MoodContract.Event.AddMood -> onNavigateToAddMood()
            MoodContract.Event.NavigateToMoodHistory -> onNavigateToMoodHistory()
        }

    MoodUI(
        records = state.records,
        onEvent = ::onEvent,
        onIntent = viewModel::onIntent
    )
}
