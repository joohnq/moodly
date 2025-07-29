package com.joohnq.auth.impl.presentation.auth

import androidx.compose.runtime.Composable
import com.joohnq.auth.impl.ui.presentation.auth.AuthNameContent
import com.joohnq.auth.impl.ui.presentation.auth.AuthNameContract
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun UserNameContentPreview() {
    AuthNameContent(
        state = AuthNameContract.State()
    )
}

@Preview
@Composable
fun UserNameContentWithNamePreview() {
    AuthNameContent(
        state =
            AuthNameContract.State(
                name = "John Doe"
            )
    )
}

@Preview
@Composable
fun UserNameContentWithErrorPreview() {
    AuthNameContent(
        state =
            AuthNameContract.State(
                name = "John Doe",
                nameError = "Some error"
            )
    )
}
