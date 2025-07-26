package com.joohnq.mood.impl.ui.parameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.joohnq.mood.impl.ui.resource.MoodAverageResource
import com.joohnq.mood.impl.ui.resource.MoodResource

class MoodAverageResourceParameterProvider : PreviewParameterProvider<MoodAverageResource> {
    override val values =
        sequenceOf(
            MoodAverageResource.Neutral,
            MoodAverageResource.Negative,
            MoodAverageResource.Skipped,
            MoodAverageResource.Positive
        )
}
