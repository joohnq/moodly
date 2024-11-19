package com.joohnq.moodapp.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.joohnq.moodapp.helper.DatetimeManager
import com.joohnq.moodapp.model.DatabaseConstants
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Entity(tableName = DatabaseConstants.HEALTH_JOURNAL_RECORD_DATABASE)
@Serializable
data class HealthJournalRecord(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val mood: Mood,
    val title: String,
    val desc: String,
    val date: LocalDateTime
) {
    class Builder {
        private var id: Int = 0
        private var mood: Mood = Mood.Neutral
        private var title: String = ""
        private var desc: String = ""
        private var date: LocalDateTime = DatetimeManager.getCurrentDateTime()

        fun setMood(mood: Mood) = apply { this.mood = mood }
        fun setTitle(title: String) = apply { this.title = title }
        fun setDesc(desc: String) = apply { this.desc = desc }
        fun setDate(date: LocalDateTime) = apply { this.date = date }

        fun build() = HealthJournalRecord(
            id = id,
            mood = mood,
            title = title,
            desc = desc,
            date = date
        )
    }

    companion object {
        fun init(): HealthJournalRecord = Builder().build()
    }
}