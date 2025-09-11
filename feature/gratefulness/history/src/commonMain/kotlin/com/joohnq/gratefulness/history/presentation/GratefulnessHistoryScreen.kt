package com.joohnq.gratefulness.history.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.joohnq.ui.sharedViewModel

@Composable
fun GratefulnessHistoryScreen(
    onGoBack: () -> Unit,
    viewModel: GratefulnessHistoryViewModel = sharedViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    fun onEvent(event: GratefulnessHistoryContract.Event) {
        when (event) {
            is GratefulnessHistoryContract.Event.GoBack -> onGoBack()
        }
    }

    GratefulnessHistoryContent(
        state = state,
        onEvent = ::onEvent
    )
}
