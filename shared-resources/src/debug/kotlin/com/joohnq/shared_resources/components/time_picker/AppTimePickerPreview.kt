package com.joohnq.shared_resources.components.time_picker

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AppTimePickerPreview() {
    AppTimePicker(
        timePickerState = rememberTimePickerState()
    )
}