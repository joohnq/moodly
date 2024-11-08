package com.joohnq.moodapp.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.joohnq.moodapp.model.DatabaseConstants
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import kotlinx.serialization.Serializable

@Entity(tableName = DatabaseConstants.SLEEP_QUALITY_RECORD_DATABASE)
@Serializable
data class SleepQualityRecord(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = DatabaseConstants.SLEEP_QUALITY) val sleepQuality: SleepQuality,
    val date: LocalDate
) {
    companion object {
        fun init(): SleepQualityRecord = SleepQualityRecord(
            id = -1,
            sleepQuality = SleepQuality.Fair,
            date = Clock.System.todayIn(TimeZone.currentSystemDefault())
        )

        fun SleepQuality.toSleepQualityRecord(): SleepQualityRecord =
            SleepQualityRecord(
                id = -1,
                sleepQuality = this,
                date = Clock.System.todayIn(TimeZone.currentSystemDefault())
            )
    }
}
