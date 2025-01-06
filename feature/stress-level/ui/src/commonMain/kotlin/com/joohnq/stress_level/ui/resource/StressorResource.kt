package com.joohnq.stress_level.ui.resource

import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.finances
import com.joohnq.shared_resources.in_peace
import com.joohnq.shared_resources.kids
import com.joohnq.shared_resources.life
import com.joohnq.shared_resources.loneliness
import com.joohnq.shared_resources.other
import com.joohnq.shared_resources.relationship
import com.joohnq.shared_resources.work
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
) : StressorProperties {
    data object Work : StressorResource(text = Res.string.work, id = WORK.id)
    data object Relationship :
        StressorResource(text = Res.string.relationship, id = RELATIONSHIP.id)

    data object Kids : StressorResource(text = Res.string.kids, id = KIDS.id)
    data object Life : StressorResource(text = Res.string.life, id = LIFE.id)
    data object Finances : StressorResource(text = Res.string.finances, id = FINANCES.id)
    data object Loneliness : StressorResource(text = Res.string.loneliness, id = LONELINESS.id)
    data object InPeace : StressorResource(text = Res.string.in_peace, id = IN_PEACE.id)
    data class Other(val other: String = "") : StressorResource(text = Res.string.other, id = other)
}