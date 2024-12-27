package com.joohnq.stress_level.ui

import androidx.compose.ui.graphics.Color
import com.joohnq.mood.theme.Colors
import com.joohnq.shared.ui.Res
import com.joohnq.shared.ui.five_number
import com.joohnq.shared.ui.four_number
import com.joohnq.shared.ui.high
import com.joohnq.shared.ui.level_five_angry
import com.joohnq.shared.ui.level_four_irritated
import com.joohnq.shared.ui.level_one_zen
import com.joohnq.shared.ui.level_three_normal
import com.joohnq.shared.ui.level_two_calm
import com.joohnq.shared.ui.mild
import com.joohnq.shared.ui.moderate
import com.joohnq.shared.ui.none
import com.joohnq.shared.ui.one_number
import com.joohnq.shared.ui.three_number
import com.joohnq.shared.ui.two_number
import com.joohnq.shared.ui.very_high
import com.joohnq.shared.ui.you_are_a_little_stressed_out
import com.joohnq.shared.ui.you_are_extremely_stressed_out
import com.joohnq.shared.ui.you_are_neutral
import com.joohnq.shared.ui.you_are_not_stressed_out
import com.joohnq.shared.ui.you_are_very_stressed_out
import com.joohnq.stress_level.domain.StressLevelProperties
import com.joohnq.stress_level.domain.entity.DStressLevelProperties
import com.joohnq.stress_level.domain.entity.StressLevel
import com.joohnq.stress_level.domain.entity.StressLevelPalette
import org.jetbrains.compose.resources.StringResource

sealed class StressLevelResource(
    override val id: Int,
    val value: StringResource,
    val text: StringResource,
    val subtitle: StringResource,
    val lifeImpact: StringResource,
    override val level: Int,
    val palette: StressLevelPalette,
) : StressLevelProperties {
    data object One :
        StressLevelResource(
            id = ONE.id,
            value = Res.string.one_number,
            text = Res.string.you_are_not_stressed_out,
            subtitle = Res.string.level_one_zen,
            level = ONE.level,
            lifeImpact = Res.string.none,
            palette = StressLevelPalette(
                color = Colors.Green60,
                backgroundColor = Colors.Green10,
            ),
        )

    data object Two :
        StressLevelResource(
            id = TWO.id,
            value = Res.string.two_number,
            text = Res.string.you_are_a_little_stressed_out,
            subtitle = Res.string.level_two_calm,
            level = TWO.level,
            lifeImpact = Res.string.mild,
            palette = StressLevelPalette(
                color = Colors.Yellow50,
                backgroundColor = Colors.Yellow10,
            ),
        )

    data object Three :
        StressLevelResource(
            id = THREE.id,
            value = Res.string.three_number,
            text = Res.string.you_are_neutral,
            subtitle = Res.string.level_three_normal,
            level = THREE.level,
            lifeImpact = Res.string.moderate,
            palette = StressLevelPalette(
                color = Colors.Yellow50,
                backgroundColor = Colors.Yellow10,
            ),
        )

    data object Four :
        StressLevelResource(
            id = FOUR.id,
            value = Res.string.four_number,
            text = Res.string.you_are_very_stressed_out,
            subtitle = Res.string.level_four_irritated,
            level = FOUR.level,
            lifeImpact = Res.string.high,
            palette = StressLevelPalette(
                color = Colors.Orange60,
                backgroundColor = Colors.Orange10,
            ),
        )

    data object Five :
        StressLevelResource(
            id = FIVE.id,
            value = Res.string.five_number,
            text = Res.string.you_are_extremely_stressed_out,
            subtitle = Res.string.level_five_angry,
            level = FIVE.level,
            lifeImpact = Res.string.very_high,
            palette = StressLevelPalette(
                color = Colors.Orange60,
                backgroundColor = Colors.Orange10,
            ),
        )

    companion object {
        val ONE = DStressLevelProperties(1, 1)
        val TWO = DStressLevelProperties(2, 2)
        val THREE = DStressLevelProperties(3, 3)
        val FOUR = DStressLevelProperties(4, 4)
        val FIVE = DStressLevelProperties(5, 5)

        fun toPercent(level: Int): Double = when (level) {
            1 -> 0.0
            2 -> 20.0
            3 -> 40.0
            4 -> 60.0
            else -> 100.0
        }

        fun getAll(): List<StressLevelResource> = listOf(
            One,
            Two,
            Three,
            Four,
            Five
        )

        fun getBrushGradient(): List<List<Color>> = listOf(
            listOf(Colors.Green50, Colors.Green50),
            listOf(Colors.Green50, Colors.Yellow50),
            listOf(Colors.Yellow50, Colors.Yellow50),
            listOf(Colors.Yellow50, Colors.Orange50),
            listOf(Colors.Orange50, Colors.Orange50)
        )

        fun fromSliderValue(value: Float): StressLevelResource = when (value) {
            0f -> One
            25f -> Two
            50f -> Three
            75f -> Four
            else -> Five
        }

        fun StressLevel.toResource(): StressLevelResource = when (this) {
            StressLevel.One -> One
            StressLevel.Two -> Two
            StressLevel.Three -> Three
            StressLevel.Four -> Four
            StressLevel.Five -> Five
        }

        fun StressLevelResource.toDomain(): StressLevel = when (this) {
            One -> StressLevel.One
            Two -> StressLevel.Two
            Three -> StressLevel.Three
            Four -> StressLevel.Four
            Five -> StressLevel.Five
        }
    }
}