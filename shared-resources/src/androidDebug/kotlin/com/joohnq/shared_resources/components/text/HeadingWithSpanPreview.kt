package com.joohnq.shared_resources.components.text

import androidx.compose.runtime.Composable
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.app_name
import com.joohnq.shared_resources.theme.Colors
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun Preview() {
    HeadingWithSpan(
        firstTitle = Res.string.app_name,
        secondTitle = Res.string.app_name,
        span = "Span",
        spanColor = Colors.Brown80
    )
}
