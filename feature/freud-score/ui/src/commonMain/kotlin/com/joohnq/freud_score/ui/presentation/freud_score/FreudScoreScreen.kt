package com.joohnq.freud_score.ui.presentation.freud_score

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.freud_score.ui.presentation.freud_score.viewmodel.FreudScoreContract
import com.joohnq.freud_score.ui.presentation.freud_score.viewmodel.FreudScoreViewModel
import com.joohnq.ui.sharedViewModel

@Composable
fun FreudScoreScreen(
    onGoBack: () -> Unit,
) {
    val freudScoreViewModel: FreudScoreViewModel = sharedViewModel()
    val state by freudScoreViewModel.state.collectAsState()

    fun onEvent(event: FreudScoreContract.Event) =
        when (event) {
            is FreudScoreContract.Event.GoBack -> onGoBack()
        }

    FreudScoreUI(
        state = state,
        onEvent = ::onEvent,
    )
}
