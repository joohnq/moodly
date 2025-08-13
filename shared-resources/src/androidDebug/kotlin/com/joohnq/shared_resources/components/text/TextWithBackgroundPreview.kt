package com.joohnq.shared_resources.components.text

import androidx.compose.runtime.Composable
import com.joohnq.shared_resources.theme.Colors
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun WithoutBorderPreview() {
    TextWithBackground(
        text = "Text",
        backgroundColor = Colors.White,
        textColor = Colors.Brown80
    )
}

@Preview
@Composable
private fun WithBorderPreview() {
    TextWithBackground(
        text = "Text",
        borderColor = Colors.Brown80,
        backgroundColor = Colors.White,
        textColor = Colors.Brown80
    )
}
