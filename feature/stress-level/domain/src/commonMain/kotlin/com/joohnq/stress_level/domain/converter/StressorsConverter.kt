package com.joohnq.stress_level.domain.converter

import com.joohnq.stress_level.domain.entity.Stressor
import com.joohnq.stress_level.domain.mapper.toStressor
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object StressorsConverter {
    fun fromStressorsList(value: List<Stressor>): String =
        Json.encodeToString(value)

    fun toStressorsList(value: String): List<Stressor> =
        Json.decodeFromString<List<String>>(value).map { it.toInt().toStressor() }
}