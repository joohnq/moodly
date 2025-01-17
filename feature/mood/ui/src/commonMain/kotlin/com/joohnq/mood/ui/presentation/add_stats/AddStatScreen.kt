package com.joohnq.mood.ui.presentation.add_stats

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.mood.ui.presentation.add_stats.event.AddStatEvent
import com.joohnq.mood.ui.presentation.add_stats.state.AddStatState
import com.joohnq.mood.ui.presentation.add_stats.viewmodel.AddStatViewModel

@Composable
fun AddStatScreen(
    onNavigateToExpressionAnalysis: () -> Unit,
    onGoBack: () -> Unit,
) {
    val addStatsViewModel: AddStatViewModel = sharedViewModel()
    val addStatsState by addStatsViewModel.state.collectAsState()

    fun onEvent(event: AddStatEvent) =
        when (event) {
            AddStatEvent.OnGoBack -> onGoBack()
            AddStatEvent.OnNavigateToExpressionAnalysis ->
                onNavigateToExpressionAnalysis()
        }

    AddStatScreenUI(
        AddStatState(
            selectedMood = addStatsState.mood,
            onEvent = ::onEvent,
            onAddAction = addStatsViewModel::onAction
        )
    )
}
