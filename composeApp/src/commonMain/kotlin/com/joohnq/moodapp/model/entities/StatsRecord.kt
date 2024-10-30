package com.joohnq.moodapp.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.joohnq.moodapp.model.DatabaseConstants
import com.joohnq.moodapp.model.converters.StatsRecordConverter
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Entity(tableName = DatabaseConstants.STATS_RECORD_DATABASE)
@Serializable
data class StatsRecord(
    @PrimaryKey(autoGenerate = true) val id: Int = 1,
    @Contextual val mood: Mood = Mood.Neutral,
    @ColumnInfo(name = DatabaseConstants.SLEEP_QUALITY) val sleepQuality: SleepQuality = SleepQuality.Fair,
    @ColumnInfo(name = DatabaseConstants.STRESS_LEVEL) val stressLevel: StressLevel = StressLevel.Three,
    val description: String = "",
    val date: LocalDate = Clock.System.todayIn(TimeZone.currentSystemDefault())
)

