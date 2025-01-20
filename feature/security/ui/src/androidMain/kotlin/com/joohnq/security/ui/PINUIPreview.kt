package com.joohnq.security.ui

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.tooling.preview.Preview
import com.joohnq.security.ui.presentation.pin.PINUI
import com.joohnq.security.ui.presentation.pin.state.PINState
import com.joohnq.security.ui.presentation.pin.viewmodel.PINViewModelState

@Preview
@Composable
fun PINUIPreview(state: PINState) {
    PINUI(
        PINState(
            snackBarState = remember { SnackbarHostState() },
            pinViewModelState = PINViewModelState(
                code = listOf(1, 2, 3, 4), focusedIndex = null
            ),
            focusRequesters = listOf(
                FocusRequester(),
                FocusRequester(),
                FocusRequester(),
                FocusRequester(),
            ),
            onAction = {},
            onEvent = {},
            canContinue = false
        )
    )
}