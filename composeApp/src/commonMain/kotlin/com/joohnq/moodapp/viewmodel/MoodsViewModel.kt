package com.joohnq.moodapp.viewmodel

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.joohnq.moodapp.model.dao.MoodsDAO
import com.joohnq.moodapp.view.entities.MoodDb
import com.joohnq.moodapp.view.entities.Mood
import com.joohnq.moodapp.view.entities.MoodDb
import com.joohnq.moodapp.view.entities.SleepQuality
import com.joohnq.moodapp.view.entities.StressLevel
import com.joohnq.moodapp.view.state.UiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
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

    /*
    * Set the onboarding current sleep quality, based on sleep quality screen
    * Tested
    * */
    fun setCurrentMoodSleepQuality(sleepQuality: SleepQuality) {
        _currentMood.value = currentMood.value?.copy(sleepQuality = sleepQuality)
    }

    /*
    * Set the onboarding current description, based expression analysis screen
    * Tested
    * */
    fun setCurrentMoodDescription(description: String) {
        _currentMood.value = currentMood.value?.copy(description = description)
    }

    /*
   * Set the onboarding current stress level, based on stress level screen
   * Tested
   * */
    fun setCurrentMoodStressLevel(stressLevel: StressLevel) {
        _currentMood.value = currentMood.value?.copy(stressLevel = stressLevel)
    }

    fun insertCurrentMood() = try {
        screenModelScope.launch(dispatcherIo) {
            moodsDAO.insertMood(currentMood.value ?: return@launch)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

    /*
    * Get all moods from database
    * Tested
    * */
    fun getMoods() = screenModelScope.launch(dispatcherIo) {
        moodsDAO.getMoods().catch {
            _moods.value = UiState.Error(it.message.toString())
        }.collect {
            _moods.value = UiState.Success(it)
        }
    }

    /*
    * Reset the currentMood value to null
    * Tested
    * */
    fun resetCurrentMood() {
        _currentMood.value = null
    }

    /* Used in testing to mock the currentMood value */
    fun setCurrentMoodForTesting(moodDb: MoodDb) {
        _currentMood.value = moodDb
    }
}