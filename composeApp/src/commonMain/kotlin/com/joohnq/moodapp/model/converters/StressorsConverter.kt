package com.joohnq.moodapp.model.converters

import androidx.room.TypeConverter
import com.joohnq.moodapp.entities.Stressors
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class StressorsConverter {
    @TypeConverter
    fun fromStressorsList(value: List<Stressors>): String =
        Json.encodeToString(value.map { it.id })

    @TypeConverter
    fun toStressorsList(value: String): List<Stressors> =
        Json.decodeFromString<List<String>>(value).map(Stressors::toValue)
}