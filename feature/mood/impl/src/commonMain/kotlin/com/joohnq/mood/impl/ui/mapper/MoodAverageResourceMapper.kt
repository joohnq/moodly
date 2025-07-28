package com.joohnq.mood.impl.ui.mapper

import com.joohnq.mood.api.entity.MoodAverage
import com.joohnq.mood.impl.ui.resource.MoodAverageResource

object MoodAverageResourceMapper {
    fun MoodAverage.toResource(): MoodAverageResource =
        when (this) {
            MoodAverage.Skipped -> MoodAverageResource.Skipped
            MoodAverage.Negative -> MoodAverageResource.Negative
            MoodAverage.Neutral -> MoodAverageResource.Neutral
            MoodAverage.Positive -> MoodAverageResource.Positive
        }
}
