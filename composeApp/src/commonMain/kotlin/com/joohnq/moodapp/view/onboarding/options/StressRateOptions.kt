package com.joohnq.moodapp.view.onboarding.options

import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.five_number
import moodapp.composeapp.generated.resources.four_number
import moodapp.composeapp.generated.resources.one_number
import moodapp.composeapp.generated.resources.three_number
import moodapp.composeapp.generated.resources.two_number
import moodapp.composeapp.generated.resources.you_are_a_little_stressed_out
import moodapp.composeapp.generated.resources.you_are_extremely_stressed_out
import moodapp.composeapp.generated.resources.you_are_neutral
import moodapp.composeapp.generated.resources.you_are_not_stressed_out
import moodapp.composeapp.generated.resources.you_are_very_stressed_out
import org.jetbrains.compose.resources.StringResource

sealed class StressRateOptions(val value: StringResource, val text: StringResource) {
    data object One : StressRateOptions(Res.string.one_number, Res.string.you_are_not_stressed_out)
    data object Two : StressRateOptions(Res.string.two_number, Res.string.you_are_a_little_stressed_out)
    data object Three : StressRateOptions(Res.string.three_number, Res.string.you_are_neutral)
    data object Four : StressRateOptions(Res.string.four_number, Res.string.you_are_very_stressed_out)
    data object Five : StressRateOptions(Res.string.five_number, Res.string.you_are_extremely_stressed_out)
}