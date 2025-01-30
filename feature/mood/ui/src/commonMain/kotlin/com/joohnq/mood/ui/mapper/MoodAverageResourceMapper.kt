package com.joohnq.mood.ui.mapper

import com.joohnq.mood.domain.entity.MoodAverage
import com.joohnq.mood.ui.resource.MoodAverageResource

fun MoodAverage.toResource(): MoodAverageResource =
    when (this) {
        MoodAverage.Skipped -> MoodAverageResource.Skipped
        MoodAverage.Negative -> MoodAverageResource.Negative
        MoodAverage.Neutral -> MoodAverageResource.Neutral
        MoodAverage.Positive -> MoodAverageResource.Positive
    }