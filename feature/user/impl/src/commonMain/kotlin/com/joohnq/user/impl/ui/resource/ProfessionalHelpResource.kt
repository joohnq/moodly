package com.joohnq.user.impl.ui.resource

import com.joohnq.domain.entity.ProfessionalHelp.Companion.NO
import com.joohnq.domain.entity.ProfessionalHelp.Companion.YES
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.no
import com.joohnq.shared_resources.yes
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
}