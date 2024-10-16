package com.joohnq.moodapp.view.entities

import androidx.compose.runtime.saveable.Saver
import com.joohnq.moodapp.Drawables
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
    val id: String,
    @Contextual val text: StringResource,
    @Contextual val icon: IconProps
) {
    @Serializable
    data object PrescribedMedications : MedicationsSupplements(
        id = PrescribedMedicationsId,
        text = Res.string.prescribed_medications,
        icon = IconProps(
            icon = Drawables.Icons.Medicine,
        )
    )

    @Serializable
    data object OverTheCounterSupplements : MedicationsSupplements(
        id = OverTheCounterSupplementsId,
        text = Res.string.over_the_counter_supplements,
        icon = IconProps(icon = Drawables.Icons.DrugStore)
    )

    @Serializable
    data object ImNotTakingAny : MedicationsSupplements(
        id = ImNotTakingAnyId,
        text = Res.string.im_not_taking_any,
        icon = IconProps(icon = Drawables.Icons.Nothing)
    )

    @Serializable
    data object PreferNotToSay :
        MedicationsSupplements(
            id = PreferNotToSayId,
            text = Res.string.prefer_not_to_say,
            icon = IconProps(icon = Drawables.Icons.Close)
        )

    companion object {
        const val PrescribedMedicationsId = "0"
        const val OverTheCounterSupplementsId = "1"
        const val ImNotTakingAnyId = "2"
        const val PreferNotToSayId = "3"

        fun valueOf(src: String): MedicationsSupplements = when (src) {
            PrescribedMedicationsId -> PrescribedMedications
            OverTheCounterSupplementsId -> OverTheCounterSupplements
            ImNotTakingAnyId -> ImNotTakingAny
            PreferNotToSayId -> PreferNotToSay
            else -> throw IllegalArgumentException("Unknown medications supplements option: $src")
        }

        fun getAll(): List<MedicationsSupplements> = listOf(
            PrescribedMedications,
            OverTheCounterSupplements,
            ImNotTakingAny,
            PreferNotToSay
        )
    }
}

val MedicationsSupplementsSaver = Saver<MedicationsSupplements?, String>(
    save = { opt -> opt?.id },
    restore = { name -> MedicationsSupplements.valueOf(name) }
)