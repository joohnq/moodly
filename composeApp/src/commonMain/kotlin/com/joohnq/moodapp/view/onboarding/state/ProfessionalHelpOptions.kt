package com.joohnq.moodapp.view.onboarding.state

import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.indeterminate
import moodapp.composeapp.generated.resources.no
import moodapp.composeapp.generated.resources.yes
import org.jetbrains.compose.resources.StringResource

sealed class ProfessionalHelpOptions(open val text: StringResource) {
    data object Yes : ProfessionalHelpOptions(Res.string.yes)
    data object No : ProfessionalHelpOptions(Res.string.no)
    data object Indeterminate :
        ProfessionalHelpOptions(Res.string.indeterminate)
}