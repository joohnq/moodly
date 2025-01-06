package com.joohnq.domain.mapper

import com.joohnq.domain.entity.PhysicalSymptoms
import com.joohnq.domain.entity.PhysicalSymptoms.Companion.NO
import com.joohnq.domain.entity.PhysicalSymptoms.Companion.YES_JUST_A_BIT
import com.joohnq.domain.entity.PhysicalSymptoms.Companion.YES_VERY_PAINFUL
import com.joohnq.domain.entity.PhysicalSymptoms.No
import com.joohnq.domain.entity.PhysicalSymptoms.YesJustABit
import com.joohnq.domain.entity.PhysicalSymptoms.YesVeryPainful

fun Int.toPhysicalSymptoms(): PhysicalSymptoms = when (this) {
    YES_VERY_PAINFUL.id -> YesVeryPainful
    NO.id -> No
    YES_JUST_A_BIT.id -> YesJustABit
    else -> throw IllegalArgumentException("Unknown physical symptoms: $this")
}

fun PhysicalSymptoms?.toInt(): Int = this?.id ?: -1

fun getAllPhysicalSymptoms(): List<PhysicalSymptoms> = listOf(YesVeryPainful, No, YesJustABit)