package com.joohnq.moodapp.domain

import com.joohnq.moodapp.ui.theme.Drawables
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.im_not_taking_any
import moodapp.composeapp.generated.resources.over_the_counter_supplements
import moodapp.composeapp.generated.resources.prefer_not_to_say
import moodapp.composeapp.generated.resources.prescribed_medications
import org.jetbrains.compose.resources.StringResource

@Serializable
sealed class MedicationsSupplements(
    val id: Int,
    @Contextual val text: StringResource,
    @Contextual val icon: Icon
) {
    @Serializable
    data object PrescribedMedications : MedicationsSupplements(
        id = PRESCRIBED_MEDICATIONS,
        text = Res.string.prescribed_medications,
        icon = Icon(
            icon = Drawables.Icons.Medicine,
            contentDescription = Res.string.prescribed_medications
        )
    )

    @Serializable
    data object OverTheCounterSupplements : MedicationsSupplements(
        id = OVER_THE_COUNTER_SUPPLEMENTS,
        text = Res.string.over_the_counter_supplements,
        icon = Icon(
            icon = Drawables.Icons.DrugStore,
            contentDescription = Res.string.over_the_counter_supplements
        )
    )

    @Serializable
    data object ImNotTakingAny : MedicationsSupplements(
        id = IM_NOT_TAKING_ANY,
        text = Res.string.im_not_taking_any,
        icon = Icon(
            icon = Drawables.Icons.Nothing,
            contentDescription = Res.string.im_not_taking_any
        )
    )

    @Serializable
    data object PreferNotToSay :
        MedicationsSupplements(
            id = PREFER_NOT_TO_SAY,
            text = Res.string.prefer_not_to_say,
            icon = Icon(
                icon = Drawables.Icons.Close,
                contentDescription = Res.string.prefer_not_to_say
            )
        )

    companion object {
        private const val PRESCRIBED_MEDICATIONS = 0
        private const val OVER_THE_COUNTER_SUPPLEMENTS = 1
        private const val IM_NOT_TAKING_ANY = 2
        private const val PREFER_NOT_TO_SAY = 3

        fun toValue(src: Int): MedicationsSupplements = when (src) {
            PRESCRIBED_MEDICATIONS -> PrescribedMedications
            OVER_THE_COUNTER_SUPPLEMENTS -> OverTheCounterSupplements
            IM_NOT_TAKING_ANY -> ImNotTakingAny
            PREFER_NOT_TO_SAY -> PreferNotToSay
            else -> throw IllegalArgumentException("Unknown medications supplements option: $src")
        }

        fun fromValue(medicationsSupplements: MedicationsSupplements?): Int =
            medicationsSupplements?.id ?: -1

        fun getAll(): List<MedicationsSupplements> = listOf(
            PrescribedMedications,
            OverTheCounterSupplements,
            ImNotTakingAny,
            PreferNotToSay
        )
    }
}
