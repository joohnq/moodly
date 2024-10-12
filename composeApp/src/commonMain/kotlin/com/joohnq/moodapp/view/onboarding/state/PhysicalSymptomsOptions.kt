package com.joohnq.moodapp.view.onboarding.state

import com.joohnq.moodapp.CustomDrawables
import com.joohnq.moodapp.view.entities.IconProps
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.indeterminate
import moodapp.composeapp.generated.resources.no_physical_pain
import moodapp.composeapp.generated.resources.yes_but_just_a_bit
import moodapp.composeapp.generated.resources.yes_very_painful
import org.jetbrains.compose.resources.StringResource

sealed class PhysicalSymptomsOptions(val text: StringResource, val icon: IconProps) {
    data object YesVeryPainful : PhysicalSymptomsOptions(
        text = Res.string.yes_very_painful, icon = IconProps(
            icon = CustomDrawables.Icons.Check,
        )
    )

    data object No : PhysicalSymptomsOptions(
        text = Res.string.no_physical_pain,
        icon = IconProps(icon = CustomDrawables.Icons.Close)
    )

    data object YesJustABit : PhysicalSymptomsOptions(
        text =Res.string.yes_but_just_a_bit,
        icon = IconProps(icon = CustomDrawables.Icons.Question)
    )

    data object Indeterminate :
        PhysicalSymptomsOptions(
            text = Res.string.indeterminate,
            icon = IconProps(icon = CustomDrawables.Icons.Question)
        )
}