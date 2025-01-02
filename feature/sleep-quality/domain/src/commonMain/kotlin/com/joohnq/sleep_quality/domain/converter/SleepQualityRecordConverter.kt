package com.joohnq.sleep_quality.domain.converter

import com.joohnq.sleep_quality.domain.entity.SleepInfluences
import com.joohnq.sleep_quality.domain.entity.SleepQuality
import com.joohnq.sleep_quality.domain.entity.SleepQuality.Companion.fromValue
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object SleepQualityRecordConverter {
    fun fromSleepQuality(value: SleepQuality): Long = value.fromValue().toLong()
    fun toSleepQuality(value: Long): SleepQuality = SleepQuality.toValue(value.toInt())

    fun fromInfluences(value: List<SleepInfluences>): String =
        Json.encodeToString(value.map { it.id })

    fun toInfluences(value: String): List<SleepInfluences> =
        Json.decodeFromString<List<Int>>(value).map(SleepInfluences::toValue)
}