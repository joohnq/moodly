package com.joohnq.moodapp.entities

import androidx.core.bundle.Bundle
import androidx.navigation.NavType
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.eygraber.uri.UriCodec
import com.joohnq.moodapp.model.DatabaseConstants
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Entity(tableName = DatabaseConstants.STATS_RECORD_DATABASE)
@Serializable
data class StatsRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val mood: Mood,
    @ColumnInfo(name = DatabaseConstants.SLEEP_QUALITY)
    val sleepQuality: SleepQuality,
    @ColumnInfo(name = DatabaseConstants.STRESS_LEVEL)
    val stressLevel: StressLevel,
    val description: String,
    val date: LocalDate
) {
    companion object {
        fun init(): StatsRecord = StatsRecord(
            id = -1,
            mood = Mood.Neutral,
            sleepQuality = SleepQuality.Fair,
            stressLevel = StressLevel.Three,
            description = "",
            date = Clock.System.todayIn(TimeZone.currentSystemDefault())
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
