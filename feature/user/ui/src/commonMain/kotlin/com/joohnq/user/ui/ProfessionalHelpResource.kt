package com.joohnq.user.ui

import com.joohnq.domain.entity.ProfessionalHelp
import com.joohnq.domain.entity.ProfessionalHelp.Companion.NO
import com.joohnq.domain.entity.ProfessionalHelp.Companion.YES
import com.joohnq.shared.ui.Res
import com.joohnq.shared.ui.no
import com.joohnq.shared.ui.yes
import org.jetbrains.compose.resources.StringResource

sealed class ProfessionalHelpResource(
    val id: Int,
    val text: StringResource,
    val value: Boolean,
) {
    data object Yes : ProfessionalHelpResource(
        id = NO.id,
        text = Res.string.yes,
        value = NO.value
    )

    data object No : ProfessionalHelpResource(
        id = YES.id,
        text = Res.string.no,
        value = YES.value
    )

    companion object {
        fun getAll(): List<ProfessionalHelpResource> = listOf(Yes, No)

        fun ProfessionalHelp.toResource(): ProfessionalHelpResource = when (this) {
            ProfessionalHelp.Yes -> Yes
            ProfessionalHelp.No -> No
        }

        fun ProfessionalHelpResource.toDomain(): ProfessionalHelp = when (this) {
            Yes -> ProfessionalHelp.Yes
            No -> ProfessionalHelp.No
        }
    }
}