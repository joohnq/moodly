package com.joohnq.api.mapper

import com.joohnq.api.entity.ProfessionalHelp
import com.joohnq.api.entity.ProfessionalHelp.Companion.NO
import com.joohnq.api.entity.ProfessionalHelp.Companion.YES
import com.joohnq.api.entity.ProfessionalHelp.No
import com.joohnq.api.entity.ProfessionalHelp.Yes

object ProfessionalHelpMapper {
    fun Long.toProfessionalHelp(): ProfessionalHelp =
        when (this) {
            YES.id -> Yes
            NO.id -> No
            else -> throw IllegalArgumentException("Unknown professional help option: $this")
        }
}
