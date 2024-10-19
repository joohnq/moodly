package com.joohnq.moodapp.viewmodel

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.joohnq.moodapp.model.dao.StatsRecordDAO
import com.joohnq.moodapp.model.entities.Mood
import com.joohnq.moodapp.model.entities.StatsRecord
import com.joohnq.moodapp.model.entities.SleepQuality
import com.joohnq.moodapp.model.entities.StressLevel
import com.joohnq.moodapp.view.state.UiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MoodsViewModel(
    private val statsRecordDAO: StatsRecordDAO,
    private val ioDispatcher: CoroutineDispatcher
) : ScreenModel {
    private val _currentMood:
            MutableStateFlow<StatsRecord?> = MutableStateFlow(null)
    val currentMood: MutableStateFlow<StatsRecord?> = _currentMood

    private val _moods:
            MutableStateFlow<UiState<List<StatsRecord>>> = MutableStateFlow(UiState.Idle)
    val moods: MutableStateFlow<UiState<List<StatsRecord>>> = _moods

    /*
    * Set the onboarding current mood, based on mood rate roulette
    * Tested
    * */
    fun setCurrentMood(mood: Mood) {
        _currentMood.value = currentMood.value?.copy(mood = mood) ?: StatsRecord(mood = mood)
    }

    /*
    * Set the onboarding current sleep quality, based on sleep quality screen
    * Tested
    * */
    fun setCurrentMoodSleepQuality(sleepQuality: SleepQuality) {
        _currentMood.value = currentMood.value?.copy(sleepQuality = sleepQuality)
            ?: StatsRecord(sleepQuality = sleepQuality)
    }

    /*
    * Set the onboarding current description, based expression analysis screen
    * Tested
    * */
    fun setCurrentMoodDescription(description: String) {
        _currentMood.value =
            currentMood.value?.copy(description = description) ?: StatsRecord(description = description)
    }

    /*
   * Set the onboarding current stress level, based on stress level screen
   * Tested
   * */
    fun setCurrentMoodStressLevel(stressLevel: StressLevel) {
        _currentMood.value =
            currentMood.value?.copy(stressLevel = stressLevel) ?: StatsRecord(stressLevel = stressLevel)
    }

    /*
    * Insert the current mood on database
    * Tested
    * */
    suspend fun insertCurrentMood(): Boolean {
        try {
            if (currentMood.value == null) throw Exception("No mood to save")
            println(currentMood.value)
            statsRecordDAO.insertMood(currentMood.value!!)
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            println("erro")
            return false
        }
    }

    /*
    * Get all moods from database
    * Tested
    * */
    fun getMoods() = screenModelScope.launch(ioDispatcher) {
        _moods.value = UiState.Loading
        statsRecordDAO.getMoods().catch {
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
    fun setCurrentMoodForTesting(statsRecord: StatsRecord) {
        _currentMood.value = statsRecord
    }
}