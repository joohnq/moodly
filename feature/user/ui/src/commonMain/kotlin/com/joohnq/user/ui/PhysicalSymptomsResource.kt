package com.joohnq.user.ui

import com.joohnq.domain.entity.PhysicalSymptoms
import com.joohnq.domain.entity.PhysicalSymptoms.Companion.NO
import com.joohnq.domain.entity.PhysicalSymptoms.Companion.YES_JUST_A_BIT
import com.joohnq.domain.entity.PhysicalSymptoms.Companion.YES_VERY_PAINFUL
import com.joohnq.shared.domain.entity.Icon
import com.joohnq.shared.ui.Res
import com.joohnq.shared.ui.no_physical_pain
import com.joohnq.shared.ui.theme.Drawables
import com.joohnq.shared.ui.yes_but_just_a_bit
import com.joohnq.shared.ui.yes_very_painful
import org.jetbrains.compose.resources.StringResource

sealed class PhysicalSymptomsResource(
    val id: Int,
    val text: StringResource,
    val icon: Icon,
) {
    data object YesVeryPainful : PhysicalSymptomsResource(
        id = YES_VERY_PAINFUL.id,
        text = Res.string.yes_very_painful,
        icon = Icon(
            icon = Drawables.Icons.Check,
            contentDescription = Res.string.yes_very_painful
        )
    )

    data object No : PhysicalSymptomsResource(
        id = NO.id,
        text = Res.string.no_physical_pain,
        icon = Icon(
            icon = Drawables.Icons.Close,
            contentDescription = Res.string.no_physical_pain
        )
    )

    data object YesJustABit : PhysicalSymptomsResource(
        id = YES_JUST_A_BIT.id,
        text = Res.string.yes_but_just_a_bit,
        icon = Icon(
            icon = Drawables.Icons.Question,
            contentDescription = Res.string.yes_but_just_a_bit
        )
    )

    companion object {
        fun getAll(): List<PhysicalSymptomsResource> = listOf(YesVeryPainful, No, YesJustABit)

        fun PhysicalSymptoms.toResource(): PhysicalSymptomsResource = when (this) {
            PhysicalSymptoms.YesVeryPainful -> YesVeryPainful
            PhysicalSymptoms.No -> No
            PhysicalSymptoms.YesJustABit -> YesJustABit
        }

        fun PhysicalSymptomsResource.toDomain(): PhysicalSymptoms = when (this) {
            YesVeryPainful -> PhysicalSymptoms.YesVeryPainful
            No -> PhysicalSymptoms.No
            YesJustABit -> PhysicalSymptoms.YesJustABit
        }
    }
}
