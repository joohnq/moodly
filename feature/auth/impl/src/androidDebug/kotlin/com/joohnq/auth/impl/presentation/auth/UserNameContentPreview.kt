package com.joohnq.auth.impl.presentation.auth

import androidx.compose.runtime.Composable
import com.joohnq.auth.impl.ui.presentation.auth.AuthNameContent
import com.joohnq.auth.impl.ui.presentation.auth.AuthNameContract
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun Preview() {
    AuthNameContent(
        state = AuthNameContract.State()
    )
}

@Preview
@Composable
private fun WithNamePreview() {
    AuthNameContent(
        state =
            AuthNameContract.State(
                name = "John Doe"
            )
    )
}

@Preview
@Composable
private fun WithErrorPreview() {
    AuthNameContent(
        state =
            AuthNameContract.State(
                name = "John Doe",
                nameError = "Some error"
            )
    )
}
