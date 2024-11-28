package com.joohnq.moodapp.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.joohnq.moodapp.data.DatabaseConstants
import com.joohnq.moodapp.util.helper.DatetimeManager
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Entity(tableName = DatabaseConstants.SLEEP_QUALITY_RECORD_DATABASE)
@Serializable
data class SleepQualityRecord(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = DatabaseConstants.SLEEP_QUALITY) val sleepQuality: SleepQuality,
    @ColumnInfo(name = DatabaseConstants.START_SLEEPING) val startSleeping: String,
    @ColumnInfo(name = DatabaseConstants.END_SLEEPING) val endSleeping: String,
    @ColumnInfo(name = DatabaseConstants.SLEEP_INFLUENCES)
    val sleepInfluences: List<SleepInfluences>,
    val date: LocalDateTime
) {
    class Builder {
        private var id: Int = 0
        private var sleepQuality: SleepQuality = SleepQuality.Fair
        private var startSleeping: String = "--:--"
        private var endSleeping: String = "--:--"
        private var sleepInfluences: List<SleepInfluences> = emptyList()
        private var date: LocalDateTime = DatetimeManager.getCurrentDateTime()

        fun setSleepQuality(sleepQuality: SleepQuality) = apply { this.sleepQuality = sleepQuality }
        fun setSleepQualityByMood(mood: Mood) =
            apply { this.sleepQuality = SleepQuality.fromMood(mood) }

        fun setStartSleeping(hour: Int, minute: Int) =
            apply { this.startSleeping = DatetimeManager.formatTime(hour, minute) }

        fun setEndSleeping(hour: Int, minute: Int) =
            apply { this.endSleeping = DatetimeManager.formatTime(hour, minute) }

        fun setSleepInfluences(sleepInfluences: List<SleepInfluences>) =
            apply { this.sleepInfluences = sleepInfluences }

        fun build() = SleepQualityRecord(
            id = id,
            sleepQuality = sleepQuality,
            startSleeping = startSleeping,
            endSleeping = endSleeping,
            sleepInfluences = sleepInfluences,
            date = date
        )
    }

    companion object {
        fun init(): SleepQualityRecord = Builder().build()
    }
}