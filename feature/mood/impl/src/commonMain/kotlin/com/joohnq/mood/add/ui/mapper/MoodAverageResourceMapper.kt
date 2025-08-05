package com.joohnq.mood.add.ui.mapper

import com.joohnq.mood.add.ui.resource.MoodAverageResource
import com.joohnq.mood.api.entity.MoodAverage

object MoodAverageResourceMapper {
    fun MoodAverage.toResource(): MoodAverageResource =
        when (this) {
            MoodAverage.Skipped -> MoodAverageResource.Skipped
            MoodAverage.Negative -> MoodAverageResource.Negative
            MoodAverage.Neutral -> MoodAverageResource.Neutral
            MoodAverage.Positive -> MoodAverageResource.Positive
        }
}
