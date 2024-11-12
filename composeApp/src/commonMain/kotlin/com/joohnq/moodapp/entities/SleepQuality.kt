package com.joohnq.moodapp.entities

import androidx.compose.runtime.saveable.Saver
import com.joohnq.moodapp.entities.palette.SleepQualityPalette
import com.joohnq.moodapp.view.ui.Colors
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
    val id: Int,
    @Contextual val firstText: StringResource,
    @Contextual val secondText: StringResource,
    val level: Int,
    val palette: SleepQualityPalette
) {
    @Serializable
    data object Excellent : SleepQuality(
        id = EXCELLENT,
        firstText = Res.string.excellent,
        secondText = Res.string.seven_nine_hours,
        level = 5,
        palette = SleepQualityPalette(
            backgroundColor2 = Colors.Green10,
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
            backgroundColor2 = Colors.Yellow10,
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
            backgroundColor2 = Colors.Brown20,
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
            backgroundColor2 = Colors.Orange10,
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
            backgroundColor2 = Colors.Purple10,
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

        fun getSaver(): Saver<SleepQuality, Int> = Saver(
            save = { fromValue(it) },
            restore = { toValue(it) }
        )
    }
}
