package com.joohnq.freud_score.impl.ui.presentation.freud_score

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.ui.observe
import com.joohnq.ui.sharedViewModel
import kotlinx.coroutines.launch

@Composable
fun FreudScoreScreen(
    snackBarState: SnackbarHostState = rememberSnackBarState(),
    onGoBack: () -> Unit,
    viewModel: FreudScoreViewModel = sharedViewModel(),
) {
    val (state, _) =
        viewModel.observe { sideEffect ->
            when (sideEffect) {
                is FreudScoreContract.SideEffect.ShowError ->
                    launch { snackBarState.showSnackbar(sideEffect.message) }
            }
        }

    fun onEvent(event: FreudScoreContract.Event) {
        when (event) {
            is FreudScoreContract.Event.GoBack -> onGoBack()
        }
    }

    FreudScoreContent(
        state = state,
        onEvent = ::onEvent
    )
}
