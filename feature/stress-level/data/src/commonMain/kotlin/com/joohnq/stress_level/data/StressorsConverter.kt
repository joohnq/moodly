package com.joohnq.stress_level.data

import androidx.room.TypeConverter
import com.joohnq.stress_level.domain.entity.Stressor
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class StressorsConverter {
    @TypeConverter
    fun fromStressorsList(value: List<Stressor>): String =
        Json.encodeToString(value.map {
            if (it::class == Stressor.Other::class) {
                val other = it as Stressor.Other
                other.other
            } else it.id
        })

    @TypeConverter
    fun toStressorsList(value: String): List<Stressor> =
        Json.decodeFromString<List<String>>(value).map(Stressor::toValue)
}