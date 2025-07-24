package com.joohnq.shared_resources.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun TextEllipsis(
    text: String,
    style: TextStyle,
    color: Color,
    maxLines: Int = 1,
    textAlign: TextAlign? = null,
) {
    Text(
        text = text,
        maxLines = maxLines,
        style = style,
        color = color,
        textAlign = textAlign,
        overflow = TextOverflow.Ellipsis
    )
}