package com.joohnq.moodapp.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.joohnq.moodapp.model.DatabaseConstants
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Entity(tableName = DatabaseConstants.STATS_RECORD_DATABASE)
@Serializable
data class StatsRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @Contextual
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
    }
}

