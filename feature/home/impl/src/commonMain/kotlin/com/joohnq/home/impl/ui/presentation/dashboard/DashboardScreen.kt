package com.joohnq.home.impl.ui.presentation.dashboard

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.ui.observe
import com.joohnq.ui.sharedViewModel
import kotlinx.coroutines.launch

@Composable
fun DashboardScreen(
    onEvent: (DashboardContract.Event) -> Unit,
    viewModel: DashboardViewModel = sharedViewModel(),
) {
    val snackBarHostState = rememberSnackBarState()
    val navigator = rememberNavController()
    val (state, onIntent) =
        viewModel.observe { sideEffect ->
            when (sideEffect) {
                is DashboardContract.SideEffect.ShowError ->
                    launch { snackBarHostState.showSnackbar(sideEffect.message) }
            }
        }

    DashboardContent(
        snackBarHostState = snackBarHostState,
        navigator = navigator,
        state = state,
        onEvent = onEvent,
        onIntent = onIntent
    )
}
