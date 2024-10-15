package com.joohnq.moodapp.view.entities

import androidx.compose.runtime.saveable.Saver
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.no
import moodapp.composeapp.generated.resources.yes
import org.jetbrains.compose.resources.StringResource


sealed class ProfessionalHelpOptions(
    val id: String,
    val text: StringResource,
    val value: Boolean? = null
) {
    
    data object Yes : ProfessionalHelpOptions(
        id = "1",
        text = Res.string.yes,
        value = true
    )

    
    data object No : ProfessionalHelpOptions(
        id = "0",
        text = Res.string.no,
        value = false
    )

    companion object {
        const val YesId = "1"
        const val NoId = "0"

        fun valueOf(src: String): ProfessionalHelpOptions = when (src) {
            YesId -> Yes
            NoId -> No
            else -> throw IllegalArgumentException("Unknown professional help option: $src")
        }
    }
}

val ProfessionalHelpOptionsSaver = Saver<ProfessionalHelpOptions?, String>(
    save = { opt -> opt?.id },
    restore = { name -> ProfessionalHelpOptions.valueOf(name) }
)

