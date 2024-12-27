package com.joohnq.domain.entity

import com.joohnq.shared.ui.Res
import com.joohnq.shared.ui.no
import com.joohnq.shared.ui.yes
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.StringResource

@Serializable
sealed class ProfessionalHelp(
    val id: Int,
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
        private const val YES = 1
        private const val NO = 0

        fun toValue(src: Int): ProfessionalHelp = when (src) {
            YES -> Yes
            NO -> No
            else -> throw IllegalArgumentException("Unknown professional help option: $src")
        }

        fun fromValue(professionalHelp: ProfessionalHelp?): Int =
            professionalHelp?.id ?: -1

        fun getAll(): List<ProfessionalHelp> = listOf(Yes, No)
    }
}
