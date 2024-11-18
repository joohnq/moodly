package com.joohnq.moodapp.view.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

@Composable
fun CalculateTextWidth(text: String, fontSize: TextUnit): Dp {
    val textMeasurer = rememberTextMeasurer()
    val textLayoutResult = textMeasurer.measure(
        text = text,
        style = TextStyle(fontSize = fontSize)
    )
    val textWidthPx = textLayoutResult.size.width
    val density = LocalDensity.current
    val textWidthDp = with(density) { textWidthPx.toDp() }

    return textWidthDp
}

@Composable
fun calculateTextHeight(text: String = "11/12", font: TextStyle): Dp {
    val density = LocalDensity.current
    val textMeasurer = rememberTextMeasurer()

    val textStyle = TextStyle(
        fontSize = font.fontSize,
        fontFamily = font.fontFamily
    )

    val textLayoutResult = textMeasurer.measure(
        text = AnnotatedString(text),
        style = textStyle
    )
    val textHeightPx = textLayoutResult.size.height.toFloat()

    return with(density) { textHeightPx.toDp() }
}