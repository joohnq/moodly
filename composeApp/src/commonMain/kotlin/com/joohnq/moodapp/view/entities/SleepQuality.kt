package com.joohnq.moodapp.view.entities

import androidx.compose.runtime.saveable.Saver
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
        id = ExcellentId,
        firstText = Res.string.excellent,
        secondText = Res.string.seven_nine_hours
    )

    @Serializable
    data object Good : SleepQuality(
        id = GoodId,
        firstText = Res.string.good,
        secondText = Res.string.six_seven_hours
    )

    @Serializable
    data object Fair : SleepQuality(
        id = FairId,
        firstText = Res.string.fair,
        secondText = Res.string.five_hours
    )

    @Serializable
    data object Poor : SleepQuality(
        id = PoorId,
        firstText = Res.string.poor,
        secondText = Res.string.three_four_hours
    )

    @Serializable
    data object Worst : SleepQuality(
        id = WorstId,
        firstText = Res.string.worst,
        secondText = Res.string.minus_three_hours
    )

    companion object {
        const val ExcellentId = "0"
        const val GoodId = "1"
        const val FairId = "2"
        const val PoorId = "3"
        const val WorstId = "4"

        fun valueOf(src: String): SleepQuality = when (src) {
            "0" -> Excellent
            "1" -> Good
            "2" -> Fair
            "3" -> Poor
            "4" -> Worst
            else -> throw IllegalArgumentException("Unknown sleep quality option: $src")
        }

        fun getAll() = listOf(
            Excellent,
            Good,
            Fair,
            Poor,
            Worst
        )
    }
}

val SleepQualitySaver = Saver<SleepQuality, String>(
    save = { opt -> opt.id },
    restore = { name -> SleepQuality.valueOf(name) }
)
