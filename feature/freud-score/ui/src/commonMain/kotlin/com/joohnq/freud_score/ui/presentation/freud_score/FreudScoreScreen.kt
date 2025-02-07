package com.joohnq.freud_score.ui.presentation.freud_score

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.freud_score.ui.presentation.freud_score.event.FreudScoreEvent
import com.joohnq.freud_score.ui.viewmodel.FreudScoreViewModel

@Composable
fun FreudScoreScreen(
    onGoBack: () -> Unit,
) {
    val freudScoreViewModel: FreudScoreViewModel = sharedViewModel()
    val state by freudScoreViewModel.state.collectAsState()

    fun onEvent(event: FreudScoreEvent) =
        when (event) {
            is FreudScoreEvent.OnGoBack -> onGoBack()
        }

    FreudScoreUI(
        state = state,
        onEvent = ::onEvent,
    )
}
