package com.joohnq.moodapp.view.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.model.entities.FreudScore
import com.joohnq.moodapp.view.constants.Colors

@Composable
@Preview
fun FreudScoreComponent(
    modifier: Modifier = Modifier,
    freudScore: FreudScore
) {
    Box(contentAlignment = Alignment.Center, modifier = modifier) {
        CircularProgressIndicator(
            progress = { freudScore.score.toFloat() / 100 },
            modifier = Modifier.fillMaxSize(),
            color = Colors.Green10,
            strokeWidth = 10.dp,
            trackColor = Colors.Green40,
            strokeCap = StrokeCap.Round
        )
        Column(modifier = Modifier.padding(horizontal = 15.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = freudScore.score.toString(), style = TextStyles.FreudScore())
            Text(text = freudScore.title, style = TextStyles.FreudTitle())
        }
    }
}


