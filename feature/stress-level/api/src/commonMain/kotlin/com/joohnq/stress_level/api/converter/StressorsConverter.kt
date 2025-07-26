package com.joohnq.stress_level.api.converter

import com.joohnq.stress_level.api.entity.Stressor
import com.joohnq.stress_level.api.mapper.toStressor
import kotlinx.serialization.json.Json

object StressorsConverter {
    fun fromStressorsList(value: List<Stressor>): String = Json.encodeToString(value.map { it.id.toString() })

    fun toStressorsList(value: String): List<Stressor> = Json.decodeFromString<List<String>>(value).map { it.toInt().toStressor() }
}
