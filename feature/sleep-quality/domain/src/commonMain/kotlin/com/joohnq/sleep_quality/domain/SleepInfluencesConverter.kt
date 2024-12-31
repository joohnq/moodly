package com.joohnq.sleep_quality.domain

import com.joohnq.sleep_quality.domain.entity.SleepInfluences
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class SleepInfluencesConverter {
    fun fromSleepInfluences(value: List<SleepInfluences>): String =
        Json.encodeToString(value.map { it.id })

    fun toStressorsList(value: String): List<SleepInfluences> =
        Json.decodeFromString<List<Int>>(value).map(SleepInfluences::toValue)
}