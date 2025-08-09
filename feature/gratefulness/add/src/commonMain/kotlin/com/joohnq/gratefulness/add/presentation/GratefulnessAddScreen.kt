package com.joohnq.gratefulness.add.presentation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import com.joohnq.ui.observe
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GratefulnessAddScreen(
    snackBarState: SnackbarHostState,
    goBack: () -> Unit,
    navigateToGratefulnessOverview: () -> Unit,
    viewModel: GratefulnessAddViewModel = koinViewModel(),
) {
    val (state, onIntent) =
        viewModel.observe { sideEffect ->
            when (sideEffect) {
                is GratefulnessAddContract.SideEffect.ShowError ->
                    launch { snackBarState.showSnackbar(sideEffect.message) }

                GratefulnessAddContract.SideEffect.NavigateToGratefulnessOverview -> navigateToGratefulnessOverview()
            }
        }

    fun onEvent(event: GratefulnessAddContract.Event) {
        when (event) {
            GratefulnessAddContract.Event.GoBack -> goBack()
        }
    }

    GratefulnessAddContent(
        state = state,
        onEvent = ::onEvent,
        onIntent = onIntent
    )
}
