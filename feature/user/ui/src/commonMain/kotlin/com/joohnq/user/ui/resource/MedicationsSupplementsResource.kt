package com.joohnq.user.ui.resource

import com.joohnq.core.ui.entity.DIcon
import com.joohnq.domain.entity.MedicationsSupplements.Companion.IM_NOT_TAKING_ANY
import com.joohnq.domain.entity.MedicationsSupplements.Companion.OVER_THE_COUNTER_SUPPLEMENTS
import com.joohnq.domain.entity.MedicationsSupplements.Companion.PREFER_NOT_TO_SAY
import com.joohnq.domain.entity.MedicationsSupplements.Companion.PRESCRIBED_MEDICATIONS
import com.joohnq.domain.property.MedicationsSupplementsProperties
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.im_not_taking_any
import com.joohnq.shared_resources.over_the_counter_supplements
import com.joohnq.shared_resources.prefer_not_to_say
import com.joohnq.shared_resources.prescribed_medications
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
            icon = Drawables.Icons.Medicine,
            contentDescription = Res.string.prescribed_medications
        )
    )

    data object OverTheCounterSupplements : MedicationsSupplementsResource(
        id = OVER_THE_COUNTER_SUPPLEMENTS.id,
        text = Res.string.over_the_counter_supplements,
        icon = DIcon(
            icon = Drawables.Icons.DrugStore,
            contentDescription = Res.string.over_the_counter_supplements
        )
    )

    data object ImNotTakingAny : MedicationsSupplementsResource(
        id = IM_NOT_TAKING_ANY.id,
        text = Res.string.im_not_taking_any,
        icon = DIcon(
            icon = Drawables.Icons.Nothing,
            contentDescription = Res.string.im_not_taking_any
        )
    )

    data object PreferNotToSay :
        MedicationsSupplementsResource(
            id = PREFER_NOT_TO_SAY.id,
            text = Res.string.prefer_not_to_say,
            icon = DIcon(
                icon = Drawables.Icons.Close,
                contentDescription = Res.string.prefer_not_to_say
            )
        )
}
