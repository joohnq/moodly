package com.joohnq.moodapp.view.onboarding.options

import androidx.compose.runtime.saveable.Saver
import com.joohnq.moodapp.Drawables
import com.joohnq.moodapp.view.entities.IconProps
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.im_not_taking_any
import moodapp.composeapp.generated.resources.indeterminate
import moodapp.composeapp.generated.resources.over_the_counter_supplements
import moodapp.composeapp.generated.resources.prefer_not_to_say
import moodapp.composeapp.generated.resources.prescribed_medications
import org.jetbrains.compose.resources.StringResource

sealed class MedicationsSupplementsOptions(
    val id: String,
    val text: StringResource,
    val icon: IconProps
) {
    data object PrescribedMedications : MedicationsSupplementsOptions(
        id = PrescribedMedicationsId,
        text = Res.string.prescribed_medications, icon = IconProps(
            icon = Drawables.Icons.Medicine,
        )
    )

    data object OverTheCounterSupplements : MedicationsSupplementsOptions(
        id = OverTheCounterSupplementsId,
        text = Res.string.over_the_counter_supplements,
        icon = IconProps(icon = Drawables.Icons.DrugStore)
    )

    data object ImNotTakingAny : MedicationsSupplementsOptions(
        id = ImNotTakingAnyId,
        text = Res.string.im_not_taking_any,
        icon = IconProps(icon = Drawables.Icons.Nothing)
    )

    data object PreferNotToSay :
        MedicationsSupplementsOptions(
            id = PreferNotToSayId,
            text = Res.string.prefer_not_to_say,
            icon = IconProps(icon = Drawables.Icons.Close)
        )

    data object Indeterminate :
        MedicationsSupplementsOptions(
            id = IndeterminateId,
            text = Res.string.indeterminate,
            icon = IconProps(icon = Drawables.Icons.Question)
        )


    companion object {
        const val PrescribedMedicationsId = "0"
        const val OverTheCounterSupplementsId = "1"
        const val ImNotTakingAnyId = "2"
        const val PreferNotToSayId = "3"
        const val IndeterminateId = "-1"

        fun valueOf(src: String): MedicationsSupplementsOptions = when (src) {
            PrescribedMedicationsId -> PrescribedMedications
            OverTheCounterSupplementsId -> OverTheCounterSupplements
            ImNotTakingAnyId -> ImNotTakingAny
            PreferNotToSayId -> PreferNotToSay
            IndeterminateId -> Indeterminate
            else -> throw IllegalArgumentException("Unknown medications supplements option: $src")
        }
    }
}

val MedicationsSupplementsOptionsSaver = Saver<MedicationsSupplementsOptions, String>(
    save = { opt -> opt.id },
    restore = { name -> MedicationsSupplementsOptions.valueOf(name) }
)