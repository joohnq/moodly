package com.joohnq.mood.api.entity

sealed interface MoodAverage {
    data object Positive : MoodAverage
    data object Neutral : MoodAverage
    data object Negative : MoodAverage
    data object Skipped : MoodAverage
}