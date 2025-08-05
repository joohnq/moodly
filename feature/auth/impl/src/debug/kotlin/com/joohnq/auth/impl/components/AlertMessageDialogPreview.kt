package com.joohnq.auth.impl.components

import androidx.compose.runtime.Composable
import com.joohnq.auth.impl.ui.components.AlertMessageDialog
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun Preview() {
    AlertMessageDialog(
        title = "Title",
        message = "Message Here",
        positiveButtonText = "Ok",
        negativeButtonText = "Cancel"
    )
}

@Preview
@Composable
private fun OnlyTitlePreview() {
    AlertMessageDialog(
        title = "Title"
    )
}

@Preview
@Composable
private fun WithoutButtonsPreview() {
    AlertMessageDialog(
        title = "Title",
        message = "Message Here"
    )
}
