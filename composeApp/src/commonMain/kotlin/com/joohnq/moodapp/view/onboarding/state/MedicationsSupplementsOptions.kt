package com.joohnq.moodapp.view.onboarding.state

import com.joohnq.moodapp.CustomDrawables
import com.joohnq.moodapp.view.entities.IconProps
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.im_not_taking_any
import moodapp.composeapp.generated.resources.indeterminate
import moodapp.composeapp.generated.resources.over_the_counter_supplements
import moodapp.composeapp.generated.resources.prefer_not_to_say
import moodapp.composeapp.generated.resources.prescribed_medications
import org.jetbrains.compose.resources.StringResource

sealed class MedicationsSupplementsOptions(val text: StringResource, val icon: IconProps) {
    data object PrescribedMedications : MedicationsSupplementsOptions(
        text = Res.string.prescribed_medications, icon = IconProps(
            icon = CustomDrawables.Icons.Medicine,
        )
    )

    data object OverTheCounterSupplements : MedicationsSupplementsOptions(
        text = Res.string.over_the_counter_supplements,
        icon = IconProps(icon = CustomDrawables.Icons.DrugStore)
    )

    data object ImNotTakingAny : MedicationsSupplementsOptions(
        text =Res.string.im_not_taking_any,
        icon = IconProps(icon = CustomDrawables.Icons.Nothing)
    )

    data object PreferNotToSay :
        MedicationsSupplementsOptions(
            text = Res.string.prefer_not_to_say,
            icon = IconProps(icon = CustomDrawables.Icons.Close)
        )

    data object Indeterminate :
        MedicationsSupplementsOptions(
            text = Res.string.indeterminate,
            icon = IconProps(icon = CustomDrawables.Icons.Question)
        )
}
