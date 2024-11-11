package com.joohnq.moodapp.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.joohnq.moodapp.helper.DatetimeHelper
import com.joohnq.moodapp.model.DatabaseConstants
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Entity(tableName = DatabaseConstants.SLEEP_QUALITY_RECORD_DATABASE)
@Serializable
data class SleepQualityRecord(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = DatabaseConstants.SLEEP_QUALITY) val sleepQuality: SleepQuality,
    val date: LocalDateTime
) {
    companion object {
        fun init(): SleepQualityRecord = SleepQualityRecord(
            id = 0,
            sleepQuality = SleepQuality.Fair,
            date = DatetimeHelper.getLocalDateTime()
        )

        fun SleepQuality.toSleepQualityRecord(): SleepQualityRecord =
            SleepQualityRecord(
                id = 0,
                sleepQuality = this,
                date = DatetimeHelper.getLocalDateTime()
            )
    }
}
