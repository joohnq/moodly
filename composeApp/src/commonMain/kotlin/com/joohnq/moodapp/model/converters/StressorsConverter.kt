package com.joohnq.moodapp.model.converters

import androidx.room.TypeConverter
import com.joohnq.moodapp.entities.Stressors
import com.joohnq.moodapp.entities.Stressors.Companion.toValue
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class StressorsConverter {
    @TypeConverter
    fun fromStressorsList(value: List<Stressors>): String =
        Json.encodeToString(value.map { it.value })

    @TypeConverter
    fun toStressorsList(value: String): List<Stressors> =
        Json.decodeFromString<List<String>>(value).map(::toValue)
}