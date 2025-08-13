package com.joohnq.shared_resources.components.card

import androidx.compose.runtime.Composable
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.app_name
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun NotAfternoonPreview() {
    AppTimePickerCard(
        title = Res.string.app_name,
        hour = "12",
        minutes = "00",
        isAfternoon = false
    )
}

@Preview
@Composable
private fun IsAfternoonPreview() {
    AppTimePickerCard(
        title = Res.string.app_name,
        hour = "12",
        minutes = "00",
        isAfternoon = true
    )
}
