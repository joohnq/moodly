package com.joohnq.self_journal.edit.components

import androidx.compose.runtime.Composable
import com.joohnq.self_journal.components.EditFloatingActionButtons
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun EditFloatingActionButtonsPreview() {
    EditFloatingActionButtons(
        isEditing = false,
        canSave = false
    )
}

@Preview
@Composable
fun EditFloatingActionButtonsIsEditingPreview() {
    EditFloatingActionButtons(
        isEditing = true,
        canSave = false
    )
}

@Preview
@Composable
fun EditFloatingActionButtonsCanSavePreview() {
    EditFloatingActionButtons(
        isEditing = false,
        canSave = true
    )
}
