package com.joohnq.mood.impl.ui.components

import androidx.compose.runtime.Composable
import com.joohnq.mood.add.ui.components.MoodFace
import com.joohnq.mood.add.ui.resource.MoodAverageResource
import com.joohnq.mood.add.ui.resource.MoodResource
import com.joohnq.mood.impl.ui.parameter.MoodAverageResourceParameterProvider
import com.joohnq.mood.impl.ui.parameter.MoodResourceParameterProvider
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Preview
@Composable
fun MoodFacePreview(
    @PreviewParameter(MoodResourceParameterProvider::class)
    resource: MoodResource,
) {
    MoodFace(
        resource = resource
    )
}

@Preview
@Composable
fun MoodFaceWithBackgroundAndColorPreview(
    @PreviewParameter(MoodResourceParameterProvider::class)
    resource: MoodResource,
) {
    MoodFace(
        backgroundColor = resource.palette.faceBackgroundColor,
        resource = resource,
        color = resource.palette.faceColor
    )
}

@Preview
@Composable
fun MoodFaceWithClickPreview(
    @PreviewParameter(MoodResourceParameterProvider::class)
    resource: MoodResource,
) {
    MoodFace(
        backgroundColor = resource.palette.faceBackgroundColor,
        color = resource.palette.faceColor,
        resource = resource,
        onClick = {}
    )
}

@Preview
@Composable
fun MoodFaceAveragePreview(
    @PreviewParameter(MoodAverageResourceParameterProvider::class)
    average: MoodAverageResource,
) {
    MoodFace(
        average = average
    )
}
