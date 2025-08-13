package com.joohnq.shared_resources.components

import androidx.compose.runtime.Composable
import com.joohnq.shared_resources.theme.Colors
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun Preview() {
    ColoredIndicatorItem(
        title = "Title",
        description = "Description",
        color = Colors.Brown80,
        isNotLast = false
    )
}

@Preview
@Composable
private fun LastPreview() {
    ColoredIndicatorItem(
        title = "Title",
        description = "Description",
        color = Colors.Brown80,
        isNotLast = true
    )
}
