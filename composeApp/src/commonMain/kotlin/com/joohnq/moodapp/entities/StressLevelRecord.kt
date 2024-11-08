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

@Entity(tableName = DatabaseConstants.STRESS_LEVEL_RECORD_DATABASE)
@Serializable
data class StressLevelRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = DatabaseConstants.STRESS_LEVEL)
    val stressLevel: StressLevel,
    val date: LocalDate
) {
    companion object {
        fun init(): StressLevelRecord = StressLevelRecord(
            id = -1,
            stressLevel = StressLevel.Three,
            date = Clock.System.todayIn(TimeZone.currentSystemDefault())
        )

        fun StressLevel.toStressLevelRecord(): StressLevelRecord =
            StressLevelRecord(
                id = -1,
                stressLevel = this,
                date = Clock.System.todayIn(TimeZone.currentSystemDefault())
            )
    }
}
