package com.joohnq.user.impl.ui.resource

import com.joohnq.api.entity.MedicationsSupplements.Companion.IM_NOT_TAKING_ANY
import com.joohnq.api.entity.MedicationsSupplements.Companion.OVER_THE_COUNTER_SUPPLEMENTS
import com.joohnq.api.entity.MedicationsSupplements.Companion.PREFER_NOT_TO_SAY
import com.joohnq.api.entity.MedicationsSupplements.Companion.PRESCRIBED_MEDICATIONS
import com.joohnq.api.property.MedicationsSupplementsProperties
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.im_not_taking_any
import com.joohnq.shared_resources.over_the_counter_supplements
import com.joohnq.shared_resources.prefer_not_to_say
import com.joohnq.shared_resources.prescribed_medications
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.ui.entity.IconResource
import org.jetbrains.compose.resources.StringResource

sealed class MedicationsSupplementsResource(
    override val id: Int,
    val text: StringResource,
    val icon: IconResource,
) : MedicationsSupplementsProperties {
    data object PrescribedMedications : MedicationsSupplementsResource(
        id = PRESCRIBED_MEDICATIONS.id,
        text = Res.string.prescribed_medications,
        icon =
            IconResource(
                icon = Drawables.Icons.Outlined.Medicine,
                contentDescription = Res.string.prescribed_medications
            )
    )

    data object OverTheCounterSupplements : MedicationsSupplementsResource(
        id = OVER_THE_COUNTER_SUPPLEMENTS.id,
        text = Res.string.over_the_counter_supplements,
        icon =
            IconResource(
                icon = Drawables.Icons.Outlined.DrugStore,
                contentDescription = Res.string.over_the_counter_supplements
            )
    )

    data object ImNotTakingAny : MedicationsSupplementsResource(
        id = IM_NOT_TAKING_ANY.id,
        text = Res.string.im_not_taking_any,
        icon =
            IconResource(
                icon = Drawables.Icons.Outlined.Nothing,
                contentDescription = Res.string.im_not_taking_any
            )
    )

    data object PreferNotToSay :
        MedicationsSupplementsResource(
            id = PREFER_NOT_TO_SAY.id,
            text = Res.string.prefer_not_to_say,
            icon =
                IconResource(
                    icon = Drawables.Icons.Outlined.Close,
                    contentDescription = Res.string.prefer_not_to_say
                )
        )
}
