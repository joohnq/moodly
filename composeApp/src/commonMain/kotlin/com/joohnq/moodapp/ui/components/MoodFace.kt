package com.joohnq.moodapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.joohnq.moodapp.domain.Mood
import com.joohnq.moodapp.ui.theme.Colors
import com.joohnq.moodapp.ui.theme.Dimens
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MoodFace(modifier: Modifier = Modifier, mood: Mood) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(color = mood.palette.faceBackgroundColor, shape = Dimens.Shape.Circle)
    ) {
        Icon(
            painter = painterResource(mood.image),
            contentDescription = null,
            modifier = modifier,
            tint = mood.palette.faceColor
        )
    }
}

@Composable
fun MoodFace(modifier: Modifier = Modifier, backgroundColor: Color, color: Color, mood: Mood) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(color = backgroundColor, shape = Dimens.Shape.Circle)
    ) {
        Icon(
            painter = painterResource(mood.image),
            contentDescription = stringResource(mood.text),
            modifier = modifier,
            tint = color
        )
    }
}

@Composable
fun MoodFace(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    color: Color,
    mood: Mood,
    onClick: () -> Unit
) {
    IconButton(
        modifier = Modifier
            .background(color = backgroundColor, shape = Dimens.Shape.Circle),
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(mood.image),
            contentDescription = stringResource(mood.text),
            modifier = modifier,
            tint = color
        )
    }
}

@Composable
fun MoodFace(
    modifier: Modifier = Modifier,
    mood: Mood,
    tint: Color,
    backgroundColor: Color = Colors.Transparent
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(color = backgroundColor, shape = Dimens.Shape.Circle)
    ) {
        Icon(
            painter = painterResource(mood.image),
            contentDescription = null,
            modifier = modifier,
            tint = tint
        )
    }
}
