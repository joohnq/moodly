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

    /*
    * Set the onboarding current mood, based on mood rate roulette
    * Tested
    * */
    fun setCurrentMood(mood: Mood) {
        _currentMood.value = currentMood.value?.copy(mood = mood) ?: MoodDb(mood = mood)
    }

    /*
    * Set the onboarding current sleep quality, based on sleep quality screen
    * Tested
    * */
    fun setCurrentMoodSleepQuality(sleepQuality: SleepQuality) {
        _currentMood.value = currentMood.value?.copy(sleepQuality = sleepQuality)
            ?: MoodDb(sleepQuality = sleepQuality)
    }

    /*
    * Set the onboarding current description, based expression analysis screen
    * Tested
    * */
    fun setCurrentMoodDescription(description: String) {
        _currentMood.value =
            currentMood.value?.copy(description = description) ?: MoodDb(description = description)
    }

    /*
   * Set the onboarding current stress level, based on stress level screen
   * Tested
   * */
    fun setCurrentMoodStressLevel(stressLevel: StressLevel) {
        _currentMood.value =
            currentMood.value?.copy(stressLevel = stressLevel) ?: MoodDb(stressLevel = stressLevel)
    }

    /*
    * Insert the current mood on database
    * Tested
    * */
    suspend fun insertCurrentMood(): Boolean {
        try {
            if (currentMood.value == null) throw Exception("No mood to save")
            moodsDAO.insertMood(currentMood.value!!)
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

    /*
    * Get all moods from database
    * Tested
    * */
    fun getMoods() = screenModelScope.launch(dispatcherIo) {
        _moods.value = UiState.Loading
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