package com.joohnq.self_journal.impl.ui.components

import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun EditFloatingActionButtonsPreview() {
    EditFloatingActionButtons(
        isEditing = false,
        canSave = false,
    )
}

@Preview
@Composable
fun EditFloatingActionButtonsIsEditingPreview() {
    EditFloatingActionButtons(
        isEditing = true,
        canSave = false,
    )
}

@Preview
@Composable
fun EditFloatingActionButtonsCanSavePreview() {
    EditFloatingActionButtons(
        isEditing = false,
        canSave = true,
    )
}