package com.joohnq.mood.ui.presentation.add_mood

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.ui.sharedViewModel
import com.joohnq.mood.ui.presentation.add_mood.viewmodel.AddMoodContract
import com.joohnq.mood.ui.presentation.add_mood.viewmodel.AddMoodViewModel

@Composable
fun AddMoodScreen(
    onNavigateToExpressionAnalysis: () -> Unit,
    onGoBack: () -> Unit,
) {
    val addStatsViewModel: AddMoodViewModel = sharedViewModel()
    val state by addStatsViewModel.state.collectAsState()

    fun onEvent(event: AddMoodContract.Event) =
        when (event) {
            AddMoodContract.Event.GoBack -> onGoBack()
            AddMoodContract.Event.NavigateToExpressionAnalysis ->
                onNavigateToExpressionAnalysis()
        }

    AddMoodUI(
        state = state,
        onEvent = ::onEvent,
        onIntent = addStatsViewModel::onIntent
    )
}
