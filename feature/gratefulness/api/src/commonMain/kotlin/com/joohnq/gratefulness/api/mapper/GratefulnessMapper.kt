package com.joohnq.gratefulness.api.mapper

import com.joohnq.api.getNow
import com.joohnq.gratefulness.api.entity.Gratefulness
import com.joohnq.gratefulness.api.entity.dto.GratefulnessDTO
import kotlin.time.ExperimentalTime

object GratefulnessMapper :
    DTODomainMapper<Gratefulness, GratefulnessDTO>,
    ListDTODomainMapper<Gratefulness, GratefulnessDTO> {
    @OptIn(ExperimentalTime::class)
    override fun GratefulnessDTO.toDomain(): Gratefulness =
        Gratefulness(
            id = id,
            iAmGratefulFor = iAmGratefulFor,
            smallThingIAppreciate = smallThingIAppreciate,
            description = description,
            createdAt = createdAt
        )

    override fun Gratefulness.toDto(): GratefulnessDTO =
        GratefulnessDTO(
            id = id,
            iAmGratefulFor = iAmGratefulFor,
            smallThingIAppreciate = smallThingIAppreciate,
            description = description
        )

    override fun List<GratefulnessDTO>.toDomain(): List<Gratefulness> = map { it.toDomain() }

    override fun List<Gratefulness>.toDto(): List<GratefulnessDTO> = map { it.toDto() }

    fun List<Gratefulness>.getTodayItem(): Gratefulness? =
        firstOrNull {
            it.createdAt.date == getNow().date
        }
}
