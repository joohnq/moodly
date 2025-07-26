package com.joohnq.mood.impl.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.joohnq.mood.impl.ui.resource.MoodAverageResource
import com.joohnq.mood.impl.ui.resource.MoodResource
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MoodFace(
    modifier: Modifier = Modifier,
    resource: MoodResource,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier =
            modifier
                .background(color = resource.palette.faceBackgroundColor, shape = Dimens.Shape.Circle)
    ) {
        Icon(
            imageVector = resource.assets.imageVector,
            contentDescription = null,
            modifier = modifier,
            tint = resource.palette.faceColor
        )
    }
}

@Composable
fun MoodFace(
    modifier: Modifier = Modifier,
    resource: MoodResource,
    backgroundColor: Color,
    color: Color,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier =
            modifier
                .background(color = backgroundColor, shape = Dimens.Shape.Circle)
    ) {
        Icon(
            imageVector = resource.assets.imageVector,
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
        modifier =
            Modifier
                .background(color = backgroundColor, shape = Dimens.Shape.Circle),
        onClick = onClick
    ) {
        Icon(
            imageVector = resource.assets.imageVector,
            contentDescription = stringResource(resource.text),
            modifier = modifier,
            tint = color
        )
    }
}

@Composable
fun MoodFace(
    modifier: Modifier = Modifier,
    average: MoodAverageResource,
) {
    Box(
        modifier =
            Modifier
                .background(color = average.color, shape = Dimens.Shape.Circle)
    ) {
        val image =
            when (average) {
                MoodAverageResource.Negative -> Drawables.Icons.Filled.MoodDepressed
                MoodAverageResource.Neutral -> Drawables.Icons.Filled.MoodNeutral
                else -> Drawables.Icons.Filled.MoodOverjoyed
            }
        Icon(
            painter = painterResource(image),
            contentDescription = null,
            modifier = modifier,
            tint = average.backgroundColor
        )
    }
}
