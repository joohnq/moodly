package com.joohnq.moodapp.model.entities

import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.excellent
import moodapp.composeapp.generated.resources.fair
import moodapp.composeapp.generated.resources.five_hours
import moodapp.composeapp.generated.resources.good
import moodapp.composeapp.generated.resources.minus_three_hours
import moodapp.composeapp.generated.resources.poor
import moodapp.composeapp.generated.resources.seven_nine_hours
import moodapp.composeapp.generated.resources.six_seven_hours
import moodapp.composeapp.generated.resources.three_four_hours
import moodapp.composeapp.generated.resources.worst
import org.jetbrains.compose.resources.StringResource

@Serializable
sealed class SleepQuality(
    val id: String,
    @Contextual val firstText: StringResource,
    @Contextual val secondText: StringResource,
) {
    @Serializable
    data object Excellent : SleepQuality(
        id = EXCELLENT,
        firstText = Res.string.excellent,
        secondText = Res.string.seven_nine_hours
    )

    @Serializable
    data object Good : SleepQuality(
        id = GOOD,
        firstText = Res.string.good,
        secondText = Res.string.six_seven_hours
    )

    @Serializable
    data object Fair : SleepQuality(
        id = FAIR,
        firstText = Res.string.fair,
        secondText = Res.string.five_hours
    )

    @Serializable
    data object Poor : SleepQuality(
        id = POOR,
        firstText = Res.string.poor,
        secondText = Res.string.three_four_hours
    )

    @Serializable
    data object Worst : SleepQuality(
        id = WORST,
        firstText = Res.string.worst,
        secondText = Res.string.minus_three_hours
    )

    companion object {
        const val EXCELLENT = "0"
        const val GOOD = "1"
        const val FAIR = "2"
        const val POOR = "3"
        const val WORST = "4"

        fun toValue(src: String): SleepQuality = when (src) {
            EXCELLENT -> Excellent
            GOOD -> Good
            FAIR -> Fair
            POOR -> Poor
            WORST -> Worst
            else -> throw IllegalArgumentException("Unknown sleep quality option: $src")
        }

        fun fromValue(sleepQuality: SleepQuality?): String = sleepQuality?.id.toString()

        fun getAll(): List<SleepQuality> = listOf(
            Excellent,
            Good,
            Fair,
            Poor,
            Worst
        )

        fun getSaver(): Saver<SleepQuality, String> = Saver(
            save = { fromValue(it) },
            restore = { toValue(it) }
        )
    }
}
