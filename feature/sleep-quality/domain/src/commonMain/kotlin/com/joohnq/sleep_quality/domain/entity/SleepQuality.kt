package com.joohnq.sleep_quality.domain.entity

import com.joohnq.mood.domain.entity.Mood
import com.joohnq.sleep_quality.domain.SleepQualityProperties
import kotlinx.serialization.Serializable

@Serializable
sealed class SleepQuality(
    override val id: Int,
    override val level: Int,
) : SleepQualityProperties {
    @Serializable
    data object Excellent : SleepQuality(EXCELLENT.id, EXCELLENT.level)

    @Serializable
    data object Good : SleepQuality(GOOD.id, GOOD.level)

    @Serializable
    data object Fair : SleepQuality(FAIR.id, FAIR.level)

    @Serializable
    data object Poor : SleepQuality(POOR.id, POOR.level)

    @Serializable
    data object Worst : SleepQuality(WORST.id, WORST.level)

    companion object {
        val EXCELLENT = DSleepQualityProperties(5, 5)
        val GOOD = DSleepQualityProperties(4, 4)
        val FAIR = DSleepQualityProperties(3, 3)
        val POOR = DSleepQualityProperties(2, 2)
        val WORST = DSleepQualityProperties(1, 1)

        fun toValue(src: Int): SleepQuality = when (src) {
            EXCELLENT.id -> Excellent
            GOOD.id -> Good
            FAIR.id -> Fair
            POOR.id -> Poor
            WORST.id -> Worst
            else -> throw IllegalArgumentException("Unknown sleep quality option: $src")
        }

        fun SleepQuality?.fromValue(): Int = this?.id ?: -1

        fun getAll(): List<SleepQuality> = listOf(
            Excellent,
            Good,
            Fair,
            Poor,
            Worst
        )

        fun Mood.toSleepQuality(): SleepQuality =
            when (this) {
                Mood.Depressed -> Worst
                Mood.Sad -> Poor
                Mood.Neutral -> Fair
                Mood.Happy -> Good
                Mood.Overjoyed -> Excellent
            }

        fun SleepQuality.toMood(): Mood = when (this) {
            Excellent -> Mood.Overjoyed
            Good -> Mood.Happy
            Fair -> Mood.Neutral
            Poor -> Mood.Sad
            Worst -> Mood.Depressed
        }
    }
}


