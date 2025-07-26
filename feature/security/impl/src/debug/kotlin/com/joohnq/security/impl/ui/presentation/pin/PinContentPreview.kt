package com.joohnq.security.impl.ui.presentation.pin

import androidx.compose.runtime.Composable
import androidx.compose.ui.focus.FocusRequester
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun PinContentPreview() {
    PinContent(
        state =
            PinContract.State(
                code = listOf(1, 2, 3, 4),
                focusedIndex = 0
            ),
        focusRequesters =
            listOf(
                FocusRequester(),
                FocusRequester(),
                FocusRequester(),
                FocusRequester()
            )
    )
}

@Preview
@Composable
fun PinContentEmptyPreview() {
    PinContent(
        state =
            PinContract.State(
                code = listOf(1, 2, 3, 4),
                focusedIndex = 0
            ),
        focusRequesters =
            listOf(
                FocusRequester(),
                FocusRequester(),
                FocusRequester(),
                FocusRequester()
            )
    )
}

@Preview
@Composable
fun PinContentCanContinuePreview() {
    PinContent(
        state =
            PinContract.State(
                code = listOf(1, 2, 3, 4),
                focusedIndex = 0
            ),
        focusRequesters =
            listOf(
                FocusRequester(),
                FocusRequester(),
                FocusRequester(),
                FocusRequester()
            )
    )
}
