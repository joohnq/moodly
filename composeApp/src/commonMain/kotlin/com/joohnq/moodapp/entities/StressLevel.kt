package com.joohnq.moodapp.entities

import androidx.compose.runtime.saveable.Saver
import androidx.compose.ui.graphics.Color
import com.joohnq.moodapp.entities.palette.StressLevelBrush
import com.joohnq.moodapp.entities.palette.StressLevelPalette
import com.joohnq.moodapp.view.constants.Colors
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.five_number
import moodapp.composeapp.generated.resources.four_number
import moodapp.composeapp.generated.resources.level_five_angry
import moodapp.composeapp.generated.resources.level_four_irritated
import moodapp.composeapp.generated.resources.level_one_zen
import moodapp.composeapp.generated.resources.level_three_normal
import moodapp.composeapp.generated.resources.level_two_calm
import moodapp.composeapp.generated.resources.one_number
import moodapp.composeapp.generated.resources.three_number
import moodapp.composeapp.generated.resources.two_number
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
    val level: Int,
    val graphValue: Double,
    val palette: StressLevelPalette,
    val brush: StressLevelBrush
) {
    @Serializable
    data object One :
        StressLevel(
            id = ONE,
            value = Res.string.one_number,
            text = Res.string.you_are_not_stressed_out,
            subtitle = Res.string.level_one_zen,
            level = 1,
            graphValue = 100.0,
            palette = StressLevelPalette(
                color = Colors.Green50,
                backgroundColor = Colors.Green10,
                pageBackgroundColor = Colors.Green60,
                pageColor = Colors.Green30
            ),
            brush = StressLevelBrush(
                color = Colors.Green50,
                backgroundColor = Colors.Green10,
            )
        )

    @Serializable
    data object Two :
        StressLevel(
            id = TWO,
            value = Res.string.two_number,
            text = Res.string.you_are_a_little_stressed_out,
            subtitle = Res.string.level_two_calm,
            level = 2,
            graphValue = 80.0,
            palette = StressLevelPalette(
                color = Colors.Yellow50,
                backgroundColor = Colors.Yellow10,
                pageBackgroundColor = Colors.Yellow60,
                pageColor = Colors.Yellow20
            ),
            brush = StressLevelBrush(
                color = Colors.Yellow50,
                backgroundColor = Colors.Yellow10,
            )
        )

    @Serializable
    data object Three :
        StressLevel(
            id = THREE,
            value = Res.string.three_number,
            text = Res.string.you_are_neutral,
            subtitle = Res.string.level_three_normal,
            level = 3,
            graphValue = 60.0,
            palette = StressLevelPalette(
                color = Colors.Brown80,
                backgroundColor = Colors.Brown40,
                pageBackgroundColor = Colors.Brown60,
                pageColor = Colors.Brown40
            ),
            brush = StressLevelBrush(
                color = Colors.Yellow50,
                backgroundColor = Colors.Yellow10,
            )
        )

    @Serializable
    data object Four :
        StressLevel(
            id = FOUR,
            value = Res.string.four_number,
            text = Res.string.you_are_very_stressed_out,
            subtitle = Res.string.level_four_irritated,
            level = 4,
            graphValue = 40.0,
            palette = StressLevelPalette(
                color = Colors.Orange80,
                backgroundColor = Colors.Orange20,
                pageBackgroundColor = Colors.Orange40,
                pageColor = Colors.Orange20
            ),
            brush = StressLevelBrush(
                color = Colors.Orange50,
                backgroundColor = Colors.Orange10,
            )
        )

    @Serializable
    data object Five :
        StressLevel(
            id = FIVE,
            value = Res.string.five_number,
            text = Res.string.you_are_extremely_stressed_out,
            subtitle = Res.string.level_five_angry,
            level = 5,
            graphValue = 20.0,
            palette = StressLevelPalette(
                color = Colors.Purple50,
                backgroundColor = Colors.Purple10,
                pageBackgroundColor = Colors.Purple40,
                pageColor = Colors.Purple20
            ),
            brush = StressLevelBrush(
                color = Colors.Orange50,
                backgroundColor = Colors.Orange10,
            )
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
    }
}
