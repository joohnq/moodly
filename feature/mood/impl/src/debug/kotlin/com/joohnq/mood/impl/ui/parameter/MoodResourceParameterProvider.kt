package com.joohnq.mood.impl.ui.parameter

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.joohnq.mood.impl.ui.resource.MoodResource

class MoodResourceParameterProvider : PreviewParameterProvider<MoodResource> {
    override val values =
        sequenceOf(
            MoodResource.Depressed,
            MoodResource.Sad,
            MoodResource.Neutral,
            MoodResource.Happy,
            MoodResource.Overjoyed
        )
}
