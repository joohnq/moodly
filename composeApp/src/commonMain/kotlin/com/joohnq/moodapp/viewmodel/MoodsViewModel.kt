package com.joohnq.moodapp.viewmodel

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.joohnq.moodapp.model.dao.MoodsDAO
import com.joohnq.moodapp.view.entities.Mood
import com.joohnq.moodapp.view.entities.MoodDb
import com.joohnq.moodapp.view.entities.SleepQuality
import com.joohnq.moodapp.view.entities.StressLevel
import com.joohnq.moodapp.view.state.UiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MoodsViewModel(
    private val moodsDAO: MoodsDAO,
    private val dispatcherIo: CoroutineDispatcher
) : ScreenModel {
    private val _currentMood:
            MutableStateFlow<MoodDb?> = MutableStateFlow(null)
    val currentMood: MutableStateFlow<MoodDb?> = _currentMood

    private val _moods:
            MutableStateFlow<UiState<List<MoodDb>>> = MutableStateFlow(UiState.Idle)
    val moods: MutableStateFlow<UiState<List<MoodDb>>> = _moods

    fun setCurrentMood(mood: Mood) {
        _currentMood.value = currentMood.value?.copy(mood = mood) ?: MoodDb(mood = mood)
    }

    fun setCurrentMoodSleepQuality(sleepQuality: SleepQuality) {
        _currentMood.value = currentMood.value?.copy(sleepQuality = sleepQuality)
    }

    fun setCurrentMoodDescription(description: String) {
        _currentMood.value = currentMood.value?.copy(description = description)
    }

    fun setCurrentMoodStressLevel(stressLevel: StressLevel) {
        _currentMood.value = currentMood.value?.copy(stressLevel = stressLevel)
    }

    suspend fun insertCurrentMood(): Boolean {
        try {
            if (currentMood.value == null)
                moodsDAO.insertMood(currentMood.value ?: return false)
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }


    fun getMoods() = screenModelScope.launch(dispatcherIo) {
        moodsDAO.getMoods().catch {
            _moods.value = UiState.Error(it.message.toString())
        }.collect {
            _moods.value = UiState.Success(it)
        }
    }

    fun resetCurrentMood() {
        _currentMood.value = null
    }
}