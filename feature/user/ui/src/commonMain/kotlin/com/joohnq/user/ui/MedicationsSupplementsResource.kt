package com.joohnq.user.ui

import com.joohnq.domain.MedicationsSupplementsProperties
import com.joohnq.domain.entity.Icon
import com.joohnq.domain.entity.MedicationsSupplements.Companion.IM_NOT_TAKING_ANY
import com.joohnq.domain.entity.MedicationsSupplements.Companion.OVER_THE_COUNTER_SUPPLEMENTS
import com.joohnq.domain.entity.MedicationsSupplements.Companion.PREFER_NOT_TO_SAY
import com.joohnq.domain.entity.MedicationsSupplements.Companion.PRESCRIBED_MEDICATIONS
import com.joohnq.mood.theme.Drawables
import com.joohnq.shared.ui.Res
import com.joohnq.shared.ui.im_not_taking_any
import com.joohnq.shared.ui.over_the_counter_supplements
import com.joohnq.shared.ui.prefer_not_to_say
import com.joohnq.shared.ui.prescribed_medications
import org.jetbrains.compose.resources.StringResource

sealed class MedicationsSupplementsResource(
    override val id: Int,
    val text: StringResource,
    val icon: Icon
) : MedicationsSupplementsProperties {
    data object PrescribedMedications : MedicationsSupplementsResource(
        id = PRESCRIBED_MEDICATIONS.id,
        text = Res.string.prescribed_medications,
        icon = Icon(
            icon = Drawables.Icons.Medicine,
            contentDescription = Res.string.prescribed_medications
        )
    )

    data object OverTheCounterSupplements : MedicationsSupplementsResource(
        id = OVER_THE_COUNTER_SUPPLEMENTS.id,
        text = Res.string.over_the_counter_supplements,
        icon = Icon(
            icon = Drawables.Icons.DrugStore,
            contentDescription = Res.string.over_the_counter_supplements
        )
    )

    data object ImNotTakingAny : MedicationsSupplementsResource(
        id = IM_NOT_TAKING_ANY.id,
        text = Res.string.im_not_taking_any,
        icon = Icon(
            icon = Drawables.Icons.Nothing,
            contentDescription = Res.string.im_not_taking_any
        )
    )

    data object PreferNotToSay :
        MedicationsSupplementsResource(
            id = PREFER_NOT_TO_SAY.id,
            text = Res.string.prefer_not_to_say,
            icon = Icon(
                icon = Drawables.Icons.Close,
                contentDescription = Res.string.prefer_not_to_say
            )
        )

    companion object {
        fun getAll(): List<MedicationsSupplementsResource> = listOf(
            PrescribedMedications,
            OverTheCounterSupplements,
            ImNotTakingAny,
            PreferNotToSay
        )
    }
}
