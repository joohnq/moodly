package com.joohnq.moodapp.viewmodel

import androidx.lifecycle.ViewModel
import com.joohnq.moodapp.model.dao.StatsRecordDAO
import com.joohnq.moodapp.entities.Mood
import com.joohnq.moodapp.entities.SleepQuality
import com.joohnq.moodapp.entities.StatsRecord
import com.joohnq.moodapp.entities.StressLevel
import com.joohnq.moodapp.view.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow

class OnboardingViewModel(
    private val statsRecordDAO: StatsRecordDAO,
) : ViewModel() {
    private val _statsRecord:
            MutableStateFlow<StatsRecord> = MutableStateFlow(StatsRecord.init())
    val statsRecord: MutableStateFlow<StatsRecord> = _statsRecord

    fun setStatsRecordMood(mood: Mood) {
        _statsRecord.value = statsRecord.value.copy(mood = mood)
    }

    fun setStatsRecordSleepQuality(sleepQuality: SleepQuality) {
        _statsRecord.value = statsRecord.value.copy(sleepQuality = sleepQuality)
    }

    fun setStatsRecordDescription(description: String) {
        _statsRecord.value =
            statsRecord.value.copy(description = description)
    }

    fun setStatsRecordStressLevel(stressLevel: StressLevel) {
        _statsRecord.value =
            statsRecord.value.copy(stressLevel = stressLevel)
    }

    suspend fun insertOnboardingStatsRecord(): Boolean = executeWithBoolean {
        statsRecordDAO.insertMood(statsRecord.value)
    }

    fun resetStatsRecord() {
        _statsRecord.value = StatsRecord.init()
    }

    fun setCurrentStatsRecord(statsRecord: StatsRecord) {
        _statsRecord.value = statsRecord
    }
}