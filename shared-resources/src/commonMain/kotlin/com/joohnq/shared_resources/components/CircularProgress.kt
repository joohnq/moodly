package com.joohnq.shared_resources.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalSmall

@Composable
@Preview
fun CircularProgressWithText(
    modifier: Modifier = Modifier,
    color: Color,
    text: String,
    textStyle: TextStyle,
    textColor: Color,
    backgroundColor: Color,
    progress: () -> Float,
) {
    Box(contentAlignment = Alignment.Center, modifier = modifier) {
        CircularProgressIndicator(
            progress = progress,
            modifier = Modifier.fillMaxSize(),
            color = color,
            strokeWidth = 10.dp,
            trackColor = backgroundColor,
            strokeCap = StrokeCap.Round
        )
        Column(
            modifier = Modifier.paddingHorizontalSmall(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = text,
                style = textStyle,
                color = textColor
            )
        }
    }
}

@Composable
@Preview
fun CircularProgressWithDoubleText(
    modifier: Modifier = Modifier,
    color: Color,
    firstText: String,
    secondText: String,
    textColor: Color,
    firstTextStyle: TextStyle,
    secondTextStyle: TextStyle,
    backgroundColor: Color,
    progress: () -> Float,
) {
    Box(contentAlignment = Alignment.Center, modifier = modifier) {
        CircularProgressIndicator(
            progress = progress,
            modifier = Modifier.fillMaxSize(),
            color = color,
            strokeWidth = 10.dp,
            trackColor = backgroundColor,
            strokeCap = StrokeCap.Round
        )
        Column(
            modifier = Modifier.padding(horizontal = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = firstText,
                style = firstTextStyle,
                color = textColor,
                textAlign = TextAlign.Center
            )
            Text(
                text = secondText,
                style = secondTextStyle,
                color = textColor,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun CircularLoading(modifier: Modifier = Modifier) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        CircularProgressIndicator(
            modifier = Modifier.size(50.dp),
            color = Colors.Brown60,
            strokeWidth = 6.dp,
            strokeCap = StrokeCap.Round
        )
    }
}



