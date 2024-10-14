package com.joohnq.moodapp.view.onboarding.options

import androidx.compose.runtime.saveable.Saver
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.indeterminate
import moodapp.composeapp.generated.resources.no
import moodapp.composeapp.generated.resources.yes
import org.jetbrains.compose.resources.StringResource

sealed class ProfessionalHelpOptions(val id: String, open val text: StringResource) {
    data object Yes : ProfessionalHelpOptions(id = "1", text = Res.string.yes)
    data object No : ProfessionalHelpOptions(id = "0", text = Res.string.no)
    data object Indeterminate :
        ProfessionalHelpOptions(id = "-1", text = Res.string.indeterminate)

    companion object {
        const val YesId = "1"
        const val NoId = "0"
        const val IndeterminateId = "-1"

        fun valueOf(src: String): ProfessionalHelpOptions = when (src) {
            YesId -> Yes
            NoId -> No
            IndeterminateId -> Indeterminate
            else -> throw IllegalArgumentException("Unknown professional help option: $src")
        }
    }
}

val ProfessionalHelpOptionsSaver = Saver<ProfessionalHelpOptions, String>(
    save = { opt -> opt.id },
    restore = { name -> ProfessionalHelpOptions.valueOf(name) }
)

