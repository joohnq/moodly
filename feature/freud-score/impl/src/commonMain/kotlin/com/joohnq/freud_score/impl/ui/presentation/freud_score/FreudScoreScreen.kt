package com.joohnq.freud_score.impl.ui.presentation.freud_score

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.ui.sharedViewModel

@Composable
fun FreudScoreScreen(
    onGoBack: () -> Unit,
    viewModel: FreudScoreViewModel = sharedViewModel(),
) {
    val state by viewModel.state.collectAsState()

    fun onEvent(event: FreudScoreContract.Event) =
        when (event) {
            is FreudScoreContract.Event.GoBack -> onGoBack()
        }

    FreudScoreContent(
        state = state,
        onEvent = ::onEvent
    )
}