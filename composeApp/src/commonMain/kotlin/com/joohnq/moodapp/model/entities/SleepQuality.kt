package com.joohnq.moodapp.model.entities

import androidx.compose.runtime.saveable.Saver
import androidx.compose.ui.graphics.Color
import com.joohnq.moodapp.view.constants.Colors
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
    @Contextual val backgroundColor: Color,
    @Contextual val color: Color,
    val level: Int
) {
    @Serializable
    data object Excellent : SleepQuality(
        id = EXCELLENT,
        firstText = Res.string.excellent,
        secondText = Res.string.seven_nine_hours,
        backgroundColor = Colors.Green10,
        color = Colors.Green50,
        level = 5
    )

    @Serializable
    data object Good : SleepQuality(
        id = GOOD,
        firstText = Res.string.good,
        secondText = Res.string.six_seven_hours,
        backgroundColor = Colors.Yellow10,
        color = Colors.Yellow50,
        level = 4
    )

    @Serializable
    data object Fair : SleepQuality(
        id = FAIR,
        firstText = Res.string.fair,
        secondText = Res.string.five_hours,
        backgroundColor = Colors.Brown20,
        color = Colors.Brown80,
        level = 3
    )

    @Serializable
    data object Poor : SleepQuality(
        id = POOR,
        firstText = Res.string.poor,
        secondText = Res.string.three_four_hours,
        backgroundColor = Colors.Orange10,
        color = Colors.Orange50,
        level = 2
    )

    @Serializable
    data object Worst : SleepQuality(
        id = WORST,
        firstText = Res.string.worst,
        secondText = Res.string.minus_three_hours,
        backgroundColor = Colors.Purple10,
        color = Colors.Purple40,
        level = 1
    )

    companion object {
        private const val EXCELLENT = "0"
        private const val GOOD = "1"
        private const val FAIR = "2"
        private const val POOR = "3"
        private const val WORST = "4"

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
