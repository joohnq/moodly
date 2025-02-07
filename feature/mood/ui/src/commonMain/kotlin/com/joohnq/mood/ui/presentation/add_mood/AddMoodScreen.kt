package com.joohnq.mood.ui.presentation.add_mood

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.mood.ui.presentation.add_mood.event.AddMoodEvent
import com.joohnq.mood.ui.presentation.add_mood.viewmodel.AddMoodViewModel

@Composable
fun AddMoodScreen(
    onNavigateToExpressionAnalysis: () -> Unit,
    onGoBack: () -> Unit,
) {
    val addStatsViewModel: AddMoodViewModel = sharedViewModel()
    val state by addStatsViewModel.state.collectAsState()

    fun onEvent(event: AddMoodEvent) =
        when (event) {
            AddMoodEvent.OnGoBack -> onGoBack()
            AddMoodEvent.OnNavigateToExpressionAnalysis ->
                onNavigateToExpressionAnalysis()
        }

    AddMoodUI(
        state = state,
        onEvent = ::onEvent,
        onAction = addStatsViewModel::onAction
    )
}
