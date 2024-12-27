package com.joohnq.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.joohnq.domain.constant.DatabaseConstants
import com.joohnq.mood.util.helper.DatetimeManager
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Entity(tableName = DatabaseConstants.STATS_RECORD_DATABASE)
@Serializable
data class StatsRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val mood: Mood,
    val description: String = "",
    @Contextual val date: LocalDateTime = DatetimeManager.getCurrentDateTime()
) {
    companion object {
        fun init(): StatsRecord = StatsRecord(
            id = 0,
            mood = Mood.Neutral,
            description = "",
            date = DatetimeManager.getCurrentDateTime()
        )
    }
}
