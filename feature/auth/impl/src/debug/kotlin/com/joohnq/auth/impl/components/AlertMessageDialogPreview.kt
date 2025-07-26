package com.joohnq.auth.impl.components

import androidx.compose.runtime.Composable
import com.joohnq.auth.impl.ui.components.AlertMessageDialog
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun AlertMessageDialogPreview() {
    AlertMessageDialog(
        title = "Title",
        message = "Message Here",
        positiveButtonText = "Ok",
        negativeButtonText = "Cancel"
    )
}

@Preview
@Composable
fun AlertMessageDialogOnlyTitlePreview() {
    AlertMessageDialog(
        title = "Title"
    )
}

@Preview
@Composable
fun AlertMessageDialogWithoutButtonsPreview() {
    AlertMessageDialog(
        title = "Title",
        message = "Message Here"
    )
}