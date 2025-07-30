package com.joohnq.mood.impl.ui.presentation.add

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.ui.sharedViewModel

@Composable
fun AddMoodScreen(
    onNavigateToExpressionAnalysis: () -> Unit,
    onGoBack: () -> Unit,
    viewModel: AddMoodViewModel = sharedViewModel(),
) {
    val state by viewModel.state.collectAsState()

    fun onEvent(event: AddMoodContract.Event) =
        when (event) {
            AddMoodContract.Event.OnGoBack -> onGoBack()
            AddMoodContract.Event.OnNavigateToExpressionAnalysis ->
                onNavigateToExpressionAnalysis()
        }

    AddMoodContent(
        state = state,
        onEvent = ::onEvent,
        onIntent = viewModel::onIntent
    )
}
