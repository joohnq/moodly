package com.joohnq.sleep_quality.api.converter

import com.joohnq.sleep_quality.api.entity.SleepInfluences
import com.joohnq.sleep_quality.api.entity.SleepQuality
import com.joohnq.sleep_quality.api.mapper.toInt
import com.joohnq.sleep_quality.api.mapper.toSleepInfluences
import com.joohnq.sleep_quality.api.mapper.toSleepQuality
import kotlinx.serialization.json.Json

object SleepQualityRecordConverter {
    fun fromSleepQuality(value: SleepQuality): Long = value.toInt().toLong()

    fun toSleepQuality(value: Long): SleepQuality = value.toInt().toSleepQuality()

    fun fromInfluences(value: List<SleepInfluences>): String = Json.encodeToString(value.map { it.id })

    fun toInfluences(value: String): List<SleepInfluences> = Json.decodeFromString<List<Int>>(value).map { it.toSleepInfluences() }
}
