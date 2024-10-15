package com.joohnq.moodapp.view.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class MoodDbConverters {
    @TypeConverter
    fun fromMood(value: Mood): String {
        return value.id
    }

    @TypeConverter
    fun toMood(value: String): Mood {
        return Mood.valueOf(value)
    }

    @TypeConverter
    fun fromSleepQuality(value: SleepQuality): String {
        return value.id
    }


    @TypeConverter
    fun toSleepQuality(value: String): SleepQuality {
        return SleepQuality.valueOf(value)
    }

    @TypeConverter
    fun fromStressLevel(value: StressLevel): String {
        return value.id
    }


    @TypeConverter
    fun toStressLevel(value: String): StressLevel {
        return StressLevel.valueOf(value)
    }

    @TypeConverter
    fun fromLocalDate(value: LocalDate): String {
        return value.toString()
    }


    @TypeConverter
    fun toLocalDate(value: String): LocalDate {
        return LocalDate.parse(value)
    }
}

@Entity(tableName = "moods")
@TypeConverters(MoodDbConverters::class)
@Serializable
data class MoodDb(
    @PrimaryKey(autoGenerate = true) val id: Int = -1,
    @Contextual val mood: Mood? = null,
    @ColumnInfo(name = "sleep_quality") val sleepQuality: SleepQuality? = null,
    @ColumnInfo(name = "stress_level") val stressLevel: StressLevel? = null,
    val description: String? = null,
    val date: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())
)