package com.joohnq.sleep_quality.domain.entity

import com.joohnq.domain.entity.Mood
import com.joohnq.mood.theme.Colors
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.StringResource

@Serializable
sealed class SleepQuality(
    val id: Int,
    @Contextual val firstText: StringResource,
    @Contextual val secondText: StringResource,
    val level: Int,
    @Contextual val palette: SleepQualityPalette
) {
    @Serializable
    data object Excellent : SleepQuality(
        id = EXCELLENT,
        firstText = Res.string.excellent,
        secondText = Res.string.seven_nine_hours,
        level = 5,
        palette = SleepQualityPalette(
            secondaryBackgroundColor = Colors.Green10,
            backgroundColor = Colors.Green30,
            color = Colors.Green60
        )
    )

    @Serializable
    data object Good : SleepQuality(
        id = GOOD,
        firstText = Res.string.good,
        secondText = Res.string.six_seven_hours,
        level = 4,
        palette = SleepQualityPalette(
            secondaryBackgroundColor = Colors.Yellow10,
            backgroundColor = Colors.Yellow30,
            color = Colors.Yellow60
        )
    )

    @Serializable
    data object Fair : SleepQuality(
        id = FAIR,
        firstText = Res.string.fair,
        secondText = Res.string.five_hours,
        level = 3,
        palette = SleepQualityPalette(
            secondaryBackgroundColor = Colors.Brown20,
            backgroundColor = Colors.Brown40,
            color = Colors.Brown70
        )
    )

    @Serializable
    data object Poor : SleepQuality(
        id = POOR,
        firstText = Res.string.poor,
        secondText = Res.string.three_four_hours,
        level = 2,
        palette = SleepQualityPalette(
            secondaryBackgroundColor = Colors.Orange10,
            backgroundColor = Colors.Orange30,
            color = Colors.Orange50
        )
    )

    @Serializable
    data object Worst : SleepQuality(
        id = WORST,
        firstText = Res.string.worst,
        secondText = Res.string.minus_three_hours,
        level = 1,
        palette = SleepQualityPalette(
            secondaryBackgroundColor = Colors.Purple10,
            backgroundColor = Colors.Purple30,
            color = Colors.Purple50
        )
    )

    companion object {
        private const val EXCELLENT = 5
        private const val GOOD = 4
        private const val FAIR = 3
        private const val POOR = 2
        private const val WORST = 1

        fun toValue(src: Int): SleepQuality = when (src) {
            EXCELLENT -> Excellent
            GOOD -> Good
            FAIR -> Fair
            POOR -> Poor
            WORST -> Worst
            else -> throw IllegalArgumentException("Unknown sleep quality option: $src")
        }

        fun fromValue(sleepQuality: SleepQuality?): Int = sleepQuality?.id ?: -1

        fun getAll(): List<SleepQuality> = listOf(
            Excellent,
            Good,
            Fair,
            Poor,
            Worst
        )

        fun fromMood(mood: Mood): SleepQuality = when (mood) {
            Mood.Depressed -> Worst
            Mood.Sad -> Poor
            Mood.Neutral -> Fair
            Mood.Happy -> Good
            Mood.Overjoyed -> Excellent
        }

        fun toMood(sleepQuality: SleepQuality): Mood = when (sleepQuality) {
            Excellent -> Mood.Overjoyed
            Good -> Mood.Happy
            Fair -> Mood.Neutral
            Poor -> Mood.Sad
            Worst -> Mood.Depressed
        }

        fun fromSliderValue(value: Float): SleepQuality = when (value) {
            0f -> Worst
            25f -> Poor
            50f -> Fair
            75f -> Good
            else -> Excellent
        }
    }
}
