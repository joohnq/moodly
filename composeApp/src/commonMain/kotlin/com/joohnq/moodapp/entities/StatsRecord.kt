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

        fun navType() = object : NavType<StatsRecord?>(
            isNullableAllowed = true
        ) {
            override fun get(bundle: Bundle, key: String): StatsRecord? {
                val jsonString = bundle.getString(key) ?: return null
                return try {
                    Json.decodeFromString(jsonString)
                } catch (e: Exception) {
                    null
                }
            }

            override fun parseValue(value: String): StatsRecord? {
                return try {
                    Json.decodeFromString(UriCodec.decode(value))
                } catch (e: Exception) {
                    null
                }
            }

            override fun put(bundle: Bundle, key: String, value: StatsRecord?) {
                bundle.putString(key, value?.let { Json.encodeToString(it) })
            }

            override fun serializeAsValue(value: StatsRecord?): String {
                return UriCodec.encode(Json.encodeToString(value ?: return ""))
            }
        }

    }
}
