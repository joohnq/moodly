package com.joohnq.moodapp.domain

import androidx.room.ColumnInfo
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
    @ColumnInfo(name = DatabaseConstants.STRESS_LEVEL) val stressLevel: StressLevel,
    val stressors: List<Stressor>,
    val date: LocalDateTime
) {
    class Builder {
        private var mood: Mood = Mood.Neutral
        private var title: String = ""
        private var description: String = ""
        private var stressLevel: StressLevel = StressLevel.Three
        private var stressors: List<Stressor> = emptyList()
        private var date: LocalDateTime = DatetimeManager.getCurrentDateTime()

        fun setMood(mood: Mood) = apply { this.mood = mood }
        fun setTitle(title: String) = apply { this.title = title }
        fun setDescription(description: String) = apply { this.description = description }
        fun setStressLevel(stressLevel: StressLevel) = apply { this.stressLevel = stressLevel }
        fun setStressors(stressors: List<Stressor>) = apply { this.stressors = stressors }

        fun build() = HealthJournalRecord(
            id = 0,
            mood = mood,
            title = title,
            description = description,
            stressLevel = stressLevel,
            stressors = stressors,
            date = date
        )
    }

    companion object {
        fun init(): HealthJournalRecord = Builder().build()
    }
}