package com.joohnq.security.impl.ui.presentation.pin

import androidx.compose.runtime.Composable
import androidx.compose.ui.focus.FocusRequester
import com.joohnq.security.impl.ui.presentation.pin.viewmodel.PINState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun PinContentPreview() {
    PinContent(
        state = PINState(
            code = listOf(1, 2, 3, 4),
            focusedIndex = 0
        ),
        focusRequesters = listOf(
            FocusRequester(),
            FocusRequester(),
            FocusRequester(),
            FocusRequester(),
        ),
        canContinue = false
    )
}

@Preview
@Composable
fun PinContentEmptyPreview() {
    PinContent(
        state = PINState(
            code = listOf(1, 2, 3, 4),
            focusedIndex = 0
        ),
        focusRequesters = listOf(
            FocusRequester(),
            FocusRequester(),
            FocusRequester(),
            FocusRequester(),
        ),
        canContinue = false
    )
}


@Preview
@Composable
fun PinContentCanContinuePreview() {
    PinContent(
        state = PINState(
            code = listOf(1, 2, 3, 4),
            focusedIndex = 0
        ),
        focusRequesters = listOf(
            FocusRequester(),
            FocusRequester(),
            FocusRequester(),
            FocusRequester(),
        ),
        canContinue = true
    )
}