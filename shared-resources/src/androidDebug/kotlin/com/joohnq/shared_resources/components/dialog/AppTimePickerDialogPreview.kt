package com.joohnq.shared_resources.components.dialog

import androidx.compose.runtime.Composable
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.app_name
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun Preview() {
    AppTimePickerDialog(
        title = Res.string.app_name
    )
}
