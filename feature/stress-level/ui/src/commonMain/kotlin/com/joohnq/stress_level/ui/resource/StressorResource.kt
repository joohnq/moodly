package com.joohnq.stress_level.ui.resource

import androidx.compose.ui.graphics.Color
import com.joohnq.shared_resources.*
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.stress_level.domain.entity.Stressor.Companion.FINANCES
import com.joohnq.stress_level.domain.entity.Stressor.Companion.IN_PEACE
import com.joohnq.stress_level.domain.entity.Stressor.Companion.KIDS
import com.joohnq.stress_level.domain.entity.Stressor.Companion.LIFE
import com.joohnq.stress_level.domain.entity.Stressor.Companion.LONELINESS
import com.joohnq.stress_level.domain.entity.Stressor.Companion.RELATIONSHIP
import com.joohnq.stress_level.domain.entity.Stressor.Companion.WORK
import com.joohnq.stress_level.domain.property.StressorProperties
import org.jetbrains.compose.resources.StringResource

sealed class StressorResource(
    val text: StringResource,
    override val id: String,
    val color: Color
) : StressorProperties {
    data object Work : StressorResource(text = Res.string.work, id = WORK.id, color = Colors.Brown60)
    data object Relationship :
        StressorResource(text = Res.string.relationship, id = RELATIONSHIP.id, color = Colors.Orange40)

    data object Kids : StressorResource(text = Res.string.kids, id = KIDS.id, color = Colors.Pink40)
    data object Life : StressorResource(text = Res.string.life, id = LIFE.id, color = Colors.Yellow40)
    data object Finances : StressorResource(text = Res.string.finances, id = FINANCES.id, color = Colors.Green70)
    data object Loneliness : StressorResource(text = Res.string.loneliness, id = LONELINESS.id, color = Colors.Gray40)
    data object InPeace : StressorResource(text = Res.string.in_peace, id = IN_PEACE.id, color = Colors.Green40)
    data class Other(val other: String = "") :
        StressorResource(text = Res.string.other, id = other, color = Colors.Purple40)
}