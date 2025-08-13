package com.joohnq.shared_resources.components

import androidx.compose.runtime.Composable
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.app_name
import com.joohnq.shared_resources.components.text.TextWithBackground
import com.joohnq.shared_resources.theme.Colors
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun LightPreview() {
    AppTopBar(
        isDark = false,
        text = Res.string.app_name
    )
}

@Preview
@Composable
private fun DarkPreview() {
    AppTopBar(
        isDark = true,
        text = Res.string.app_name
    )
}

@Preview
@Composable
private fun LightWithContentPreview() {
    AppTopBar(
        isDark = false,
        text = Res.string.app_name,
        content = {
            TextWithBackground(
                text = "Text",
                textColor = Colors.Brown80,
                backgroundColor = Colors.White
            )
        }
    )
}

@Preview
@Composable
private fun DarkWithContentPreview() {
    AppTopBar(
        isDark = true,
        text = Res.string.app_name,
        content = {
            TextWithBackground(
                text = "Text",
                textColor = Colors.Brown80,
                backgroundColor = Colors.White
            )
        }
    )
}
