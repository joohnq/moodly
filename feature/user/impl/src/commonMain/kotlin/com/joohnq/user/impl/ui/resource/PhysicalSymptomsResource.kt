package com.joohnq.user.impl.ui.resource

import com.joohnq.ui.entity.DIcon
import com.joohnq.api.entity.PhysicalSymptoms.Companion.NO
import com.joohnq.api.entity.PhysicalSymptoms.Companion.YES_JUST_A_BIT
import com.joohnq.api.entity.PhysicalSymptoms.Companion.YES_VERY_PAINFUL
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.no_physical_pain
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.yes_but_just_a_bit
import com.joohnq.shared_resources.yes_very_painful
import org.jetbrains.compose.resources.StringResource

sealed class PhysicalSymptomsResource(
    val id: Int,
    val text: StringResource,
    val icon: DIcon,
) {
    data object YesVeryPainful : PhysicalSymptomsResource(
        id = YES_VERY_PAINFUL.id,
        text = Res.string.yes_very_painful,
        icon = DIcon(
            icon = Drawables.Icons.Outlined.Check,
            contentDescription = Res.string.yes_very_painful
        )
    )

    data object No : PhysicalSymptomsResource(
        id = NO.id,
        text = Res.string.no_physical_pain,
        icon = DIcon(
            icon = Drawables.Icons.Outlined.Close,
            contentDescription = Res.string.no_physical_pain
        )
    )

    data object YesJustABit : PhysicalSymptomsResource(
        id = YES_JUST_A_BIT.id,
        text = Res.string.yes_but_just_a_bit,
        icon = DIcon(
            icon = Drawables.Icons.Outlined.Question,
            contentDescription = Res.string.yes_but_just_a_bit
        )
    )
}
