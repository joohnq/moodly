package com.joohnq.gratefulness.history.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.ui.sharedViewModel

@Composable
fun GratefulnessHistoryScreen(
    onGoBack: () -> Unit,
    viewModel: GratefulnessHistoryViewModel = sharedViewModel(),
) {
    val state by viewModel.state.collectAsState()

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
