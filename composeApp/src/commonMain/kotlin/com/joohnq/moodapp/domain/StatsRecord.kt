package com.joohnq.moodapp.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.joohnq.moodapp.data.DatabaseConstants
import com.joohnq.moodapp.util.helper.DatetimeManager
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Entity(tableName = DatabaseConstants.STATS_RECORD_DATABASE)
@Serializable
data class StatsRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val mood: Mood,
    val description: String,
    @Contextual val date: LocalDateTime
) {
    class Builder {
        private var id: Int = 0
        private var mood: Mood = Mood.Neutral
        private var description: String = ""
        private var date: LocalDateTime = DatetimeManager.getCurrentDateTime()

        fun setMood(mood: Mood) = apply { this.mood = mood }
        fun setDescription(description: String) = apply { this.description = description }

        fun build() = StatsRecord(
            id = id,
            mood = mood,
            description = description,
            date = date
        )
    }

    companion object {
        fun init(): StatsRecord = StatsRecord(
            id = 0,
            mood = Mood.Neutral,
            description = "",
            date = DatetimeManager.getCurrentDateTime()
        )
    }
}
