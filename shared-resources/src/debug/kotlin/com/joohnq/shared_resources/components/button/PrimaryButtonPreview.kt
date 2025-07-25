package com.joohnq.shared_resources.components.button

import androidx.compose.runtime.Composable
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.app_name
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun PrimaryButtonPreview() {
    PrimaryButton(
        text = Res.string.app_name,
    )
}