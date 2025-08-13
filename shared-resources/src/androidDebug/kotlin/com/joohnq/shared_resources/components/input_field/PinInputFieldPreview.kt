package com.joohnq.shared_resources.components.input_field

import androidx.compose.runtime.Composable
import androidx.compose.ui.focus.FocusRequester
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun Preview() {
    PinInputField(
        number = 1,
        focusRequester = FocusRequester()
    )
}
