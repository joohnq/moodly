package com.joohnq.shared_resources.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun CalculateTextWidth(text: StringResource, fontSize: TextUnit): Dp {
    val textMeasurer = rememberTextMeasurer()
    val textLayoutResult = textMeasurer.measure(
        text = stringResource(text),
        style = TextStyle(fontSize = fontSize)
    )
    val textWidthPx = textLayoutResult.size.width
    val density = LocalDensity.current
    val textWidthDp = with(density) { textWidthPx.toDp() }

    return textWidthDp
}