package com.joohnq.moodapp.entities

import androidx.core.bundle.Bundle
import androidx.navigation.NavType
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.eygraber.uri.UriCodec
import com.joohnq.moodapp.helper.DatetimeHelper
import com.joohnq.moodapp.model.DatabaseConstants
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Entity(tableName = DatabaseConstants.STATS_RECORD_DATABASE)
@Serializable
data class StatsRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val mood: Mood,
    val description: String,
    val date: LocalDateTime
) {
    companion object {
        fun init(): StatsRecord = StatsRecord(
            id = 0,
            mood = Mood.Neutral,
            description = "",
            date = DatetimeHelper.getLocalDateTime()
        )

        fun navType() = object : NavType<StatsRecord>(
            isNullableAllowed = false,
        ) {
            override fun get(bundle: Bundle, key: String): StatsRecord? {
                return Json.decodeFromString(bundle.getString(key) ?: return null)
            }

            override fun parseValue(value: String): StatsRecord {
                return Json.decodeFromString(UriCodec.decode(value))
            }

            override fun put(bundle: Bundle, key: String, value: StatsRecord) {
                bundle.putString(key, Json.encodeToString(value))
            }

            override fun serializeAsValue(value: StatsRecord): String {
                return UriCodec.encode(Json.encodeToString(value))
            }
        }
    }
}
