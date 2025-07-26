package com.joohnq.user.impl.ui.mapper

import com.joohnq.api.entity.PhysicalSymptoms
import com.joohnq.user.impl.ui.resource.PhysicalSymptomsResource
import com.joohnq.user.impl.ui.resource.PhysicalSymptomsResource.No
import com.joohnq.user.impl.ui.resource.PhysicalSymptomsResource.YesJustABit
import com.joohnq.user.impl.ui.resource.PhysicalSymptomsResource.YesVeryPainful

fun getAllPhysicalSymptomsResource(): List<PhysicalSymptomsResource> = listOf(YesVeryPainful, No, YesJustABit)

fun PhysicalSymptoms.toResource(): PhysicalSymptomsResource =
    when (this) {
        PhysicalSymptoms.YesVeryPainful -> YesVeryPainful
        PhysicalSymptoms.No -> No
        PhysicalSymptoms.YesJustABit -> YesJustABit
    }

fun PhysicalSymptomsResource.toDomain(): PhysicalSymptoms =
    when (this) {
        YesVeryPainful -> PhysicalSymptoms.YesVeryPainful
        No -> PhysicalSymptoms.No
        YesJustABit -> PhysicalSymptoms.YesJustABit
    }
