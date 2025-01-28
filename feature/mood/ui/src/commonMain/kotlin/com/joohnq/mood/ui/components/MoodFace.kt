package com.joohnq.mood.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.joohnq.mood.ui.resource.MoodResource
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MoodFace(modifier: Modifier = Modifier, resource: MoodResource) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(color = resource.palette.faceBackgroundColor, shape = Dimens.Shape.Circle)
    ) {
        Icon(
            painter = painterResource(resource.image),
            contentDescription = null,
            modifier = modifier,
            tint = resource.palette.faceColor
        )
    }
}

@Composable
fun MoodFace(
    resource: MoodResource,
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    color: Color,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(color = backgroundColor, shape = Dimens.Shape.Circle)
    ) {
        Icon(
            painter = painterResource(resource.image),
            contentDescription = stringResource(resource.text),
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
    resource: MoodResource,
    onClick: () -> Unit,
) {
    IconButton(
        modifier = Modifier
            .background(color = backgroundColor, shape = Dimens.Shape.Circle),
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(resource.image),
            contentDescription = stringResource(resource.text),
            modifier = modifier,
            tint = color
        )
    }
}

@Composable
fun MoodFace(
    modifier: Modifier = Modifier,
    mood: MoodResource,
    tint: Color,
    backgroundColor: Color = Colors.Transparent,
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
