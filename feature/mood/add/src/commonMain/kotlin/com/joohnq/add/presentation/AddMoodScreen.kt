package com.joohnq.add.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.joohnq.ui.sharedViewModel

@Composable
fun AddMoodScreen(
    onNavigateToExpressionAnalysis: () -> Unit,
    onGoBack: () -> Unit,
    viewModel: AddMoodViewModel = sharedViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    fun onEvent(event: AddMoodContract.Event) {
        when (event) {
            AddMoodContract.Event.GoBack -> onGoBack()
            AddMoodContract.Event.NavigateNext ->
                onNavigateToExpressionAnalysis()
        }
    }

    AddMoodContent(
        state = state,
        onEvent = ::onEvent,
        onIntent = viewModel::onIntent
    )
}
