package com.joohnq.moodapp.entities

import androidx.compose.runtime.saveable.Saver
import androidx.compose.ui.graphics.Color
import com.joohnq.moodapp.entities.palette.StressLevelPalette
import com.joohnq.moodapp.view.ui.Colors
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.five_number
import moodapp.composeapp.generated.resources.four_number
import moodapp.composeapp.generated.resources.high
import moodapp.composeapp.generated.resources.level_five_angry
import moodapp.composeapp.generated.resources.level_four_irritated
import moodapp.composeapp.generated.resources.level_one_zen
import moodapp.composeapp.generated.resources.level_three_normal
import moodapp.composeapp.generated.resources.level_two_calm
import moodapp.composeapp.generated.resources.mild
import moodapp.composeapp.generated.resources.moderate
import moodapp.composeapp.generated.resources.none
import moodapp.composeapp.generated.resources.one_number
import moodapp.composeapp.generated.resources.three_number
import moodapp.composeapp.generated.resources.two_number
import moodapp.composeapp.generated.resources.very_high
import moodapp.composeapp.generated.resources.you_are_a_little_stressed_out
import moodapp.composeapp.generated.resources.you_are_extremely_stressed_out
import moodapp.composeapp.generated.resources.you_are_neutral
import moodapp.composeapp.generated.resources.you_are_not_stressed_out
import moodapp.composeapp.generated.resources.you_are_very_stressed_out
import org.jetbrains.compose.resources.StringResource

@Serializable
sealed class StressLevel(
    val id: Int,
    @Contextual val value: StringResource,
    @Contextual val text: StringResource,
    @Contextual val subtitle: StringResource,
    @Contextual val lifeImpact: StringResource,
    val level: Int,
    val palette: StressLevelPalette,
) {
    @Serializable
    data object One :
        StressLevel(
            id = ONE,
            value = Res.string.one_number,
            text = Res.string.you_are_not_stressed_out,
            subtitle = Res.string.level_one_zen,
            level = 1,
            lifeImpact = Res.string.none,
            palette = StressLevelPalette(
                color = Colors.Green60,
                backgroundColor = Colors.Green10,
            ),
        )

    @Serializable
    data object Two :
        StressLevel(
            id = TWO,
            value = Res.string.two_number,
            text = Res.string.you_are_a_little_stressed_out,
            subtitle = Res.string.level_two_calm,
            level = 2,
            lifeImpact = Res.string.mild,
            palette = StressLevelPalette(
                color = Colors.Yellow50,
                backgroundColor = Colors.Yellow10,
            ),
        )

    @Serializable
    data object Three :
        StressLevel(
            id = THREE,
            value = Res.string.three_number,
            text = Res.string.you_are_neutral,
            subtitle = Res.string.level_three_normal,
            level = 3,
            lifeImpact = Res.string.moderate,
            palette = StressLevelPalette(
                color = Colors.Yellow50,
                backgroundColor = Colors.Yellow10,
            ),
        )

    @Serializable
    data object Four :
        StressLevel(
            id = FOUR,
            value = Res.string.four_number,
            text = Res.string.you_are_very_stressed_out,
            subtitle = Res.string.level_four_irritated,
            level = 4,
            lifeImpact = Res.string.high,
            palette = StressLevelPalette(
                color = Colors.Orange60,
                backgroundColor = Colors.Orange10,
            ),
        )

    @Serializable
    data object Five :
        StressLevel(
            id = FIVE,
            value = Res.string.five_number,
            text = Res.string.you_are_extremely_stressed_out,
            subtitle = Res.string.level_five_angry,
            level = 5,
            lifeImpact = Res.string.very_high,
            palette = StressLevelPalette(
                color = Colors.Orange60,
                backgroundColor = Colors.Orange10,
            ),
        )

    companion object {
        private const val ONE = 1
        private const val TWO = 2
        private const val THREE = 3
        private const val FOUR = 4
        private const val FIVE = 5

        fun toValue(src: Int): StressLevel = when (src) {
            ONE -> One
            TWO -> Two
            THREE -> Three
            FOUR -> Four
            FIVE -> Five
            else -> throw IllegalArgumentException("Unknown stress rate option: $src")
        }

        fun toPercent(level: Int): Double = when (level) {
            1 -> 0.0
            2 -> 20.0
            3 -> 40.0
            4 -> 60.0
            else -> 100.0
        }

        fun fromValue(stressLevel: StressLevel?): Int = stressLevel?.id ?: -1

        fun getAll(): List<StressLevel> = listOf(
            One,
            Two,
            Three,
            Four,
            Five
        )

        fun getSaver(): Saver<StressLevel, Int> = Saver(
            save = { fromValue(it) },
            restore = { toValue(it) }
        )

        fun getBrushGradient(): List<List<Color>> = listOf(
            listOf(Colors.Green50, Colors.Green50),
            listOf(Colors.Green50, Colors.Yellow50),
            listOf(Colors.Yellow50, Colors.Yellow50),
            listOf(Colors.Yellow50, Colors.Orange50),
            listOf(Colors.Orange50, Colors.Orange50)
        )

        fun fromSliderValue(value: Float): StressLevel = when (value) {
            0f -> One
            25f -> Two
            50f -> Three
            75f -> Four
            else -> Five
        }
    }
}
