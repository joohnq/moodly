package com.joohnq.api.mapper

import com.joohnq.api.entity.ProfessionalHelp
import com.joohnq.api.entity.ProfessionalHelp.Companion.NO
import com.joohnq.api.entity.ProfessionalHelp.Companion.YES
import com.joohnq.api.entity.ProfessionalHelp.No
import com.joohnq.api.entity.ProfessionalHelp.Yes

fun Int.toProfessionalHelp(): ProfessionalHelp = when (this) {
    YES.id -> Yes
    NO.id -> No
    else -> throw IllegalArgumentException("Unknown professional help option: $this")
}

fun ProfessionalHelp?.toInt(): Int = this?.id ?: -1

fun getAllProfessionalHelp(): List<ProfessionalHelp> = listOf(Yes, No)