package com.joohnq.security.impl.ui.presentation.pin

import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun Preview() {
    PinContent(
        state =
            PinContract.State(
                code = listOf(1, 2, 3, 4),
                focusedIndex = 0
            )
    )
}

@Preview
@Composable
private fun EmptyPreview() {
    PinContent(
        state =
            PinContract.State(
                code = listOf(),
                focusedIndex = 0
            )
    )
}
