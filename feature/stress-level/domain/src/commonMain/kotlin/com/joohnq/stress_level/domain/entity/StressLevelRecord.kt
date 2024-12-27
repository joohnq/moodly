package com.joohnq.stress_level.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.joohnq.mood.util.helper.DatetimeManager
import com.joohnq.stress_level.domain.constant.DatabaseConstants
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Entity(tableName = DatabaseConstants.STRESS_LEVEL_RECORD_DATABASE)
@Serializable
data class StressLevelRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = DatabaseConstants.STRESS_LEVEL)
    val stressLevel: StressLevel,
    val stressors: List<Stressor> = emptyList(),
    val date: LocalDateTime = DatetimeManager.getCurrentDateTime()
) {
    companion object {
        fun init(): StressLevelRecord = StressLevelRecord(
            stressLevel = StressLevel.Three
        )
    }
}
