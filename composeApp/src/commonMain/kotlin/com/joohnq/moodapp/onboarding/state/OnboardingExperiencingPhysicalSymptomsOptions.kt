package com.joohnq.moodapp.onboarding.state

import com.joohnq.moodapp.Drawables
import com.joohnq.moodapp.entities.IconProps
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.indeterminate
import moodapp.composeapp.generated.resources.no_physical_pain
import moodapp.composeapp.generated.resources.yes_but_just_a_bit
import moodapp.composeapp.generated.resources.yes_very_painful
import org.jetbrains.compose.resources.StringResource

sealed class OnboardingExperiencingPhysicalSymptomsOptions(val text: StringResource, val icon: IconProps) {
    data object YesVeryPainful : OnboardingExperiencingPhysicalSymptomsOptions(
        text = Res.string.yes_very_painful, icon = IconProps(
            icon = Drawables.Icons.Check,
        )
    )

    data object No : OnboardingExperiencingPhysicalSymptomsOptions(
        text = Res.string.no_physical_pain,
        icon = IconProps(icon = Drawables.Icons.Close)
    )

    data object YesJustABit : OnboardingExperiencingPhysicalSymptomsOptions(
        text =Res.string.yes_but_just_a_bit,
        icon = IconProps(icon = Drawables.Icons.Question)
    )

    data object Indeterminate :
        OnboardingExperiencingPhysicalSymptomsOptions(
            text = Res.string.indeterminate,
            icon = IconProps(icon = Drawables.Icons.Question)
        )
}