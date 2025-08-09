package com.joohnq.gratefulness.api.mapper

interface DTODomainMapper<DOMAIN, DTO> {
    fun DTO.toDomain(): DOMAIN

    fun DOMAIN.toDto(): DTO
}
