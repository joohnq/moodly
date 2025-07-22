package com.joohnq.mood.api.mapper

import com.joohnq.mood.api.entity.Mood
import com.joohnq.mood.api.entity.MoodAverage

fun Int.toAverage(): MoodAverage =
    when (this) {
        in Mood.Depressed.healthLevel - 20..Mood.Sad.healthLevel -> MoodAverage.Negative
        in Mood.Neutral.healthLevel - 20..Mood.Neutral.healthLevel -> MoodAverage.Neutral
        in Mood.Happy.healthLevel - 20..Mood.Overjoyed.healthLevel -> MoodAverage.Positive
        else -> MoodAverage.Skipped
    }