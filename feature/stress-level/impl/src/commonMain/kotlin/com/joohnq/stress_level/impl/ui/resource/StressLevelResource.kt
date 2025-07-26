package com.joohnq.stress_level.impl.ui.resource

import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.five_number
import com.joohnq.shared_resources.four_number
import com.joohnq.shared_resources.high
import com.joohnq.shared_resources.level_five_angry
import com.joohnq.shared_resources.level_four_irritated
import com.joohnq.shared_resources.level_one_zen
import com.joohnq.shared_resources.level_three_normal
import com.joohnq.shared_resources.level_two_calm
import com.joohnq.shared_resources.mild
import com.joohnq.shared_resources.moderate
import com.joohnq.shared_resources.none
import com.joohnq.shared_resources.one_number
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.three_number
import com.joohnq.shared_resources.two_number
import com.joohnq.shared_resources.very_high
import com.joohnq.shared_resources.you_are_a_little_stressed_out
import com.joohnq.shared_resources.you_are_extremely_stressed_out
import com.joohnq.shared_resources.you_are_neutral
import com.joohnq.shared_resources.you_are_not_stressed_out
import com.joohnq.shared_resources.you_are_very_stressed_out
import com.joohnq.stress_level.api.entity.StressLevel.Companion.FIVE
import com.joohnq.stress_level.api.entity.StressLevel.Companion.FOUR
import com.joohnq.stress_level.api.entity.StressLevel.Companion.ONE
import com.joohnq.stress_level.api.entity.StressLevel.Companion.THREE
import com.joohnq.stress_level.api.entity.StressLevel.Companion.TWO
import com.joohnq.stress_level.api.entity.StressLevelPalette
import com.joohnq.stress_level.api.property.StressLevelProperties
import org.jetbrains.compose.resources.StringResource

sealed class StressLevelResource(
    override val id: Int,
    val value: StringResource,
    val text: StringResource,
    val subtitle: StringResource,
    val lifeImpact: StringResource,
    override val level: Int,
    val palette: StressLevelPalette
) : StressLevelProperties {
    data object One :
        StressLevelResource(
            id = ONE.id,
            value = Res.string.one_number,
            text = Res.string.you_are_not_stressed_out,
            subtitle = Res.string.level_one_zen,
            level = ONE.level,
            lifeImpact = Res.string.none,
            palette =
                StressLevelPalette(
                    color = Colors.Green60,
                    backgroundColor = Colors.Green10,
                    imageColor = Colors.Green70
                )
        )

    data object Two :
        StressLevelResource(
            id = TWO.id,
            value = Res.string.two_number,
            text = Res.string.you_are_a_little_stressed_out,
            subtitle = Res.string.level_two_calm,
            level = TWO.level,
            lifeImpact = Res.string.mild,
            palette =
                StressLevelPalette(
                    color = Colors.Yellow50,
                    backgroundColor = Colors.Yellow10,
                    imageColor = Colors.Yellow60
                )
        )

    data object Three :
        StressLevelResource(
            id = THREE.id,
            value = Res.string.three_number,
            text = Res.string.you_are_neutral,
            subtitle = Res.string.level_three_normal,
            level = THREE.level,
            lifeImpact = Res.string.moderate,
            palette =
                StressLevelPalette(
                    color = Colors.Yellow50,
                    backgroundColor = Colors.Yellow10,
                    imageColor = Colors.Yellow60
                )
        )

    data object Four :
        StressLevelResource(
            id = FOUR.id,
            value = Res.string.four_number,
            text = Res.string.you_are_very_stressed_out,
            subtitle = Res.string.level_four_irritated,
            level = FOUR.level,
            lifeImpact = Res.string.high,
            palette =
                StressLevelPalette(
                    color = Colors.Orange60,
                    backgroundColor = Colors.Orange10,
                    imageColor = Colors.Orange50
                )
        )

    data object Five :
        StressLevelResource(
            id = FIVE.id,
            value = Res.string.five_number,
            text = Res.string.you_are_extremely_stressed_out,
            subtitle = Res.string.level_five_angry,
            level = FIVE.level,
            lifeImpact = Res.string.very_high,
            palette =
                StressLevelPalette(
                    color = Colors.Orange60,
                    backgroundColor = Colors.Orange10,
                    imageColor = Colors.Orange50
                )
        )
}
