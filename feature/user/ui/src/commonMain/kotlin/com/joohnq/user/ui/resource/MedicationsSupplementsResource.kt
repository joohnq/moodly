package com.joohnq.user.ui.resource

import com.joohnq.core.ui.entity.DIcon
import com.joohnq.domain.entity.MedicationsSupplements.Companion.IM_NOT_TAKING_ANY
import com.joohnq.domain.entity.MedicationsSupplements.Companion.OVER_THE_COUNTER_SUPPLEMENTS
import com.joohnq.domain.entity.MedicationsSupplements.Companion.PREFER_NOT_TO_SAY
import com.joohnq.domain.entity.MedicationsSupplements.Companion.PRESCRIBED_MEDICATIONS
import com.joohnq.domain.property.MedicationsSupplementsProperties
import com.joohnq.shared_resources.*
import com.joohnq.shared_resources.theme.Drawables
import org.jetbrains.compose.resources.StringResource

sealed class MedicationsSupplementsResource(
    override val id: Int,
    val text: StringResource,
    val icon: DIcon,
) : MedicationsSupplementsProperties {
    data object PrescribedMedications : MedicationsSupplementsResource(
        id = PRESCRIBED_MEDICATIONS.id,
        text = Res.string.prescribed_medications,
        icon = DIcon(
            icon = Drawables.Icons.Outlined.Medicine,
            contentDescription = Res.string.prescribed_medications
        )
    )

    data object OverTheCounterSupplements : MedicationsSupplementsResource(
        id = OVER_THE_COUNTER_SUPPLEMENTS.id,
        text = Res.string.over_the_counter_supplements,
        icon = DIcon(
            icon = Drawables.Icons.Outlined.DrugStore,
            contentDescription = Res.string.over_the_counter_supplements
        )
    )

    data object ImNotTakingAny : MedicationsSupplementsResource(
        id = IM_NOT_TAKING_ANY.id,
        text = Res.string.im_not_taking_any,
        icon = DIcon(
            icon = Drawables.Icons.Outlined.Nothing,
            contentDescription = Res.string.im_not_taking_any
        )
    )

    data object PreferNotToSay :
        MedicationsSupplementsResource(
            id = PREFER_NOT_TO_SAY.id,
            text = Res.string.prefer_not_to_say,
            icon = DIcon(
                icon = Drawables.Icons.Outlined.Close,
                contentDescription = Res.string.prefer_not_to_say
            )
        )
}
