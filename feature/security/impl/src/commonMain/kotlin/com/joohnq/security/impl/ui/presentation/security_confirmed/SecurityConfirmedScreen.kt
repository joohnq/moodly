package com.joohnq.security.impl.ui.presentation.security_confirmed

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.ui.sharedViewModel

@Composable
fun SecurityConfirmedScreen(
    navigateNext: () -> Unit,
    viewModel: SecurityConfirmedViewModel = sharedViewModel(),
) {
    val snackBarState = rememberSnackBarState()

    LaunchedEffect(viewModel.sideEffect) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                SecurityConfirmedContract.SideEffect.NavigateNext -> navigateNext()
                is SecurityConfirmedContract.SideEffect.ShowError ->
                    snackBarState.showSnackbar(sideEffect.message)
            }
        }
    }

    SecurityConfirmedContent(
        onIntent = viewModel::onIntent
    )
}
