package com.joohnq.shared_resources.components.text

import androidx.compose.runtime.Composable
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun TextEllipsisPreview() {
    TextEllipsis(
        text = "Text",
        style = TextStyles.textSmMedium(),
        color = Colors.Brown80
    )
}
