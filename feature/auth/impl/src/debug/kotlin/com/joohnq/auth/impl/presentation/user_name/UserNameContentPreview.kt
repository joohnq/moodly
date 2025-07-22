package com.joohnq.auth.impl.presentation.user_name

import androidx.compose.runtime.Composable
import com.joohnq.auth.impl.presentation.user_name.viewmodel.UserNameState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun UserNameContentPreview() {
    UserNameContent(
        state = UserNameState(),
        onEvent = {},
        onClearFocus = {},
        onGetAction = {},
    )
}

@Preview
@Composable
fun UserNameContentPreviewWithName() {
    UserNameContent(
        state = UserNameState(
            name = "John Doe"
        ),
        onEvent = {},
        onClearFocus = {},
        onGetAction = {},
    )
}

@Preview
@Composable
fun UserNameContentPreviewWithError() {
    UserNameContent(
        state = UserNameState(
            name = "John Doe",
            nameError = "Some error"
        ),
        onEvent = {},
        onClearFocus = {},
        onGetAction = {},
    )
}