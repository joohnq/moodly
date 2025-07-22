package com.joohnq.auth.impl.components

import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun AlertMessageDialogPreview() {
    AlertMessageDialog(
        title = "Title",
        message = "Message Here",
        positiveButtonText = "Ok",
        negativeButtonText = "Cancel",
    )
}

@Preview
@Composable
fun AlertMessageDialogPreviewOnlyTitle() {
    AlertMessageDialog(
        title = "Title",
    )
}

@Preview
@Composable
fun AlertMessageDialogPreviewWithoutButtons() {
    AlertMessageDialog(
        title = "Title",
        message = "Message Here",
    )
}