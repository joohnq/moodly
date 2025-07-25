package com.joohnq.shared_resources.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.api.mapper.toFormattedDateString
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.app_name
import com.joohnq.shared_resources.components.text.TextWithBackground
import com.joohnq.shared_resources.theme.Colors
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun AppTopBarLightPreview() {
    AppTopBar(
        isDark = false,
        text = Res.string.app_name,
    )
}

@Preview
@Composable
fun AppTopBarDarkPreview() {
    AppTopBar(
        isDark = true,
        text = Res.string.app_name,
    )
}

@Preview
@Composable
fun AppTopBarLightWithContentPreview() {
    AppTopBar(
        isDark = false,
        text = Res.string.app_name,
        content = {
            TextWithBackground(
                text = "Text",
                textColor = Colors.Brown80,
                backgroundColor = Colors.White,
            )
        }
    )
}

@Preview
@Composable
fun AppTopBarDarkWithContentPreview() {
    AppTopBar(
        isDark = true,
        text = Res.string.app_name,
        content = {
            TextWithBackground(
                text = "Text",
                textColor = Colors.Brown80,
                backgroundColor = Colors.White,
            )
        }
    )
}