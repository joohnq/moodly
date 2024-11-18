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
        private var date: LocalDateTime = DatetimeHelper.getLocalDateTime()

        fun setSleepQuality(sleepQuality: SleepQuality) = apply { this.sleepQuality = sleepQuality }
        fun setStartSleeping(startSleeping: String) = apply { this.startSleeping = startSleeping }
        fun setEndSleeping(endSleeping: String) = apply { this.endSleeping = endSleeping }
        fun setSleepInfluences(sleepInfluences: List<SleepInfluences>) =
            apply { this.sleepInfluences = sleepInfluences }

        fun setDate(date: LocalDateTime) = apply { this.date = date }

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