package com.joohnq.sleep_quality.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.joohnq.mood.util.helper.DatetimeManager
import com.joohnq.sleep_quality.domain.constant.DatabaseConstants
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Entity(tableName = DatabaseConstants.SLEEP_QUALITY_RECORD_DATABASE)
@Serializable
data class SleepQualityRecord(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = DatabaseConstants.SLEEP_QUALITY) val sleepQuality: SleepQuality,
    @ColumnInfo(name = DatabaseConstants.START_SLEEPING) val startSleeping: String = "--:--",
    @ColumnInfo(name = DatabaseConstants.END_SLEEPING) val endSleeping: String = "--:--",
    @ColumnInfo(name = DatabaseConstants.SLEEP_INFLUENCES)
    val sleepInfluences: List<SleepInfluences> = emptyList(),
    val date: LocalDateTime = DatetimeManager.getCurrentDateTime()
) {
    companion object {
        fun SleepQualityRecord.startSleeping(hour: Int, minute: Int): SleepQualityRecord =
            this.copy(
                startSleeping = DatetimeManager.formatTime(hour, minute)
            )

        fun SleepQualityRecord.endSleeping(hour: Int, minute: Int): SleepQualityRecord =
            this.copy(
                endSleeping = DatetimeManager.formatTime(hour, minute)
            )

        fun init(): SleepQualityRecord = SleepQualityRecord(
            sleepQuality = SleepQuality.Fair,
        )
    }
}