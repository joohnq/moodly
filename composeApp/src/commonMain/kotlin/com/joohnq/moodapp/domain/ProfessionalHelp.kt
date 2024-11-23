package com.joohnq.moodapp.domain

import androidx.compose.runtime.saveable.Saver
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.no
import moodapp.composeapp.generated.resources.yes
import org.jetbrains.compose.resources.StringResource

@Serializable
sealed class ProfessionalHelp(
    val id: String,
    @Contextual val text: StringResource,
    val value: Boolean
) {
    @Serializable
    data object Yes : ProfessionalHelp(
        id = NO,
        text = Res.string.yes,
        value = true
    )

    @Serializable
    data object No : ProfessionalHelp(
        id = YES,
        text = Res.string.no,
        value = false
    )

    companion object {
        private const val YES = "1"
        private const val NO = "0"

        fun toValue(src: String): ProfessionalHelp = when (src) {
            YES -> Yes
            NO -> No
            else -> throw IllegalArgumentException("Unknown professional help option: $src")
        }

        fun fromValue(professionalHelp: ProfessionalHelp?): String =
            professionalHelp?.id.toString()

        fun getAll(): List<ProfessionalHelp> = listOf(Yes, No)

        fun getSaver(): Saver<ProfessionalHelp?, String> = Saver(
            save = { fromValue(it) },
            restore = { toValue(it) }
        )
    }
}
