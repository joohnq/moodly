package com.joohnq.domain.entity

import com.joohnq.domain.getNow
import com.joohnq.domain.serializer.MedicationsSupplementsSerializer
import com.joohnq.domain.serializer.PhysicalSymptomsSerializer
import com.joohnq.domain.serializer.ProfessionalHelpSerializer
import dev.gitlive.firebase.firestore.Timestamp
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Serializable
data class User @OptIn(ExperimentalUuidApi::class) constructor(
    val id: String = "",
    val name: String? = null,
    val email: String? = null,
    val image: UserImage? = null,
    @Serializable(with = MedicationsSupplementsSerializer::class)
    val medicationsSupplements: MedicationsSupplements? = null,
    @Serializable(with = ProfessionalHelpSerializer::class)
    val soughtHelp: ProfessionalHelp? = null,
    @Serializable(with = PhysicalSymptomsSerializer::class)
    val physicalSymptoms: PhysicalSymptoms? = null,
    val createdAt: String = getNow().toString(),
)
