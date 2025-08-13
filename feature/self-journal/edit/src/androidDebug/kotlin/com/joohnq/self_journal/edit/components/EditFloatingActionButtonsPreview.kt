package com.joohnq.self_journal.edit.components

import androidx.compose.runtime.Composable
import com.joohnq.self_journal.components.EditFloatingActionButtons
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun Preview() {
    EditFloatingActionButtons(
        isEditing = false,
        canSave = false
    )
}

@Preview
@Composable
private fun IsEditingPreview() {
    EditFloatingActionButtons(
        isEditing = true,
        canSave = false
    )
}

@Preview
@Composable
private fun CanSavePreview() {
    EditFloatingActionButtons(
        isEditing = false,
        canSave = true
    )
}
