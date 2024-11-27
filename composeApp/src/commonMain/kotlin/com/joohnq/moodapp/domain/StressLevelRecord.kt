package com.joohnq.moodapp.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.joohnq.moodapp.data.DatabaseConstants
import com.joohnq.moodapp.util.helper.DatetimeManager
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Entity(tableName = DatabaseConstants.STRESS_LEVEL_RECORD_DATABASE)
@Serializable
data class StressLevelRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = DatabaseConstants.STRESS_LEVEL)
    val stressLevel: StressLevel,
    val stressors: List<Stressor>,
    val date: LocalDateTime
) {
    class Builder {
        private var id: Int = 0
        private var stressLevel: StressLevel = StressLevel.Three
        private var stressors: List<Stressor> = emptyList()
        private var date: LocalDateTime = DatetimeManager.getCurrentDateTime()

        fun setStressLevel(stressLevel: StressLevel) = apply { this.stressLevel = stressLevel }
        fun setStressors(stressors: List<Stressor>) =
            apply { this.stressors = stressors }

        fun build() = StressLevelRecord(
            id = id,
            stressLevel = stressLevel,
            stressors = stressors,
            date = date
        )
    }

    companion object {
        fun init(): StressLevelRecord = Builder().build()
    }
}
