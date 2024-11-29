package com.joohnq.moodapp.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.joohnq.moodapp.data.DatabaseConstants
import com.joohnq.moodapp.util.helper.DatetimeManager
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Entity(tableName = DatabaseConstants.HEALTH_JOURNAL_RECORD_DATABASE)
@Serializable
data class HealthJournalRecord(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val mood: Mood,
    val title: String,
    val description: String,
    val date: LocalDateTime
) {
    class Builder {
        private var mood: Mood = Mood.Neutral
        private var title: String = ""
        private var description: String = ""
        private var date: LocalDateTime = DatetimeManager.getCurrentDateTime()

        fun setMood(mood: Mood) = apply { this.mood = mood }
        fun setTitle(title: String) = apply { this.title = title }
        fun setDescription(description: String) = apply { this.description = description }

        fun build() = HealthJournalRecord(
            id = 0,
            mood = mood,
            title = title,
            description = description,
            date = date
        )
    }

    companion object {
        fun init(): HealthJournalRecord = Builder().build()
    }
}