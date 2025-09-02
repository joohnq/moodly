package com.joohnq.stress_level.impl.ui.resource

import androidx.compose.ui.graphics.Color
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.finances
import com.joohnq.shared_resources.in_peace
import com.joohnq.shared_resources.kids
import com.joohnq.shared_resources.life
import com.joohnq.shared_resources.loneliness
import com.joohnq.shared_resources.other
import com.joohnq.shared_resources.relationship
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.work
import com.joohnq.stress_level.api.entity.Stressor.Companion.FINANCES
import com.joohnq.stress_level.api.entity.Stressor.Companion.IN_PEACE
import com.joohnq.stress_level.api.entity.Stressor.Companion.KIDS
import com.joohnq.stress_level.api.entity.Stressor.Companion.LIFE
import com.joohnq.stress_level.api.entity.Stressor.Companion.LONELINESS
import com.joohnq.stress_level.api.entity.Stressor.Companion.OTHER
import com.joohnq.stress_level.api.entity.Stressor.Companion.RELATIONSHIP
import com.joohnq.stress_level.api.entity.Stressor.Companion.WORK
import com.joohnq.stress_level.api.property.StressorProperties
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

sealed class StressorResource(
    val text: StringResource,
    override val id: Long,
    val color: Color,
    val icon: DrawableResource,
) : StressorProperties {
    data object Work : StressorResource(
        text = Res.string.work,
        id = WORK.id,
        color = Colors.Brown60,
        icon = Drawables.Icons.Outlined.Work
    )

    data object Relationship :
        StressorResource(
            text = Res.string.relationship,
            id = RELATIONSHIP.id,
            color = Colors.Orange40,
            icon = Drawables.Icons.Outlined.Work
        )

    data object Kids : StressorResource(
        text = Res.string.kids,
        id = KIDS.id,
        color = Colors.Pink40,
        icon = Drawables.Icons.Outlined.Work
    )

    data object Life : StressorResource(
        text = Res.string.life,
        id = LIFE.id,
        color = Colors.Yellow40,
        icon = Drawables.Icons.Outlined.Work
    )

    data object Finances : StressorResource(
        text = Res.string.finances,
        id = FINANCES.id,
        color = Colors.Green70,
        icon = Drawables.Icons.Outlined.Work
    )

    data object Loneliness : StressorResource(
        text = Res.string.loneliness,
        id = LONELINESS.id,
        color = Colors.Gray40,
        icon = Drawables.Icons.Outlined.Work
    )

    data object InPeace : StressorResource(
        text = Res.string.in_peace,
        id = IN_PEACE.id,
        color = Colors.Green40,
        icon = Drawables.Icons.Outlined.Work
    )

    data object Other :
        StressorResource(
            text = Res.string.other,
            id = OTHER.id,
            color = Colors.Purple40,
            icon = Drawables.Icons.Outlined.Work
        )

    companion object {
        val allStressors =
            listOf(
                Work,
                Relationship,
                Kids,
                Life,
                Finances,
                Loneliness,
                InPeace,
                Other
            )
    }
}
