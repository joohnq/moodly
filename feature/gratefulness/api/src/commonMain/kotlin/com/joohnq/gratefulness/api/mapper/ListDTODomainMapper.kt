package com.joohnq.gratefulness.api.mapper

interface ListDTODomainMapper<DOMAIN, DTO> {
    fun List<DTO>.toDomain(): List<DOMAIN>

    fun List<DOMAIN>.toDto(): List<DTO>
}
