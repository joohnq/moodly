package com.joohnq.moodapp.viewmodel

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.joohnq.moodapp.model.MoodDb
import com.joohnq.moodapp.view.entities.Mood
import com.joohnq.moodapp.view.entities.SleepQuality
import com.joohnq.moodapp.view.entities.StressLevel
import com.joohnq.moodapp.view.state.UiState
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MoodsViewModel(
    private val realm: Realm,
    private val dispatcherIo: CoroutineDispatcher
) : ScreenModel {
    private val _currentMood:
            MutableStateFlow<MoodDb?> = MutableStateFlow(null)
    val currentMood: MutableStateFlow<MoodDb?> = _currentMood

    private val _moods:
            MutableStateFlow<UiState<List<MoodDb>>> = MutableStateFlow(UiState.Idle)
    val moods: MutableStateFlow<UiState<List<MoodDb>>> = _moods

    val realmMoods = realm.query<MoodDb>().find().asFlow()

    fun setCurrentMood(mood: Mood) {
        _currentMood.value =
            currentMood.value?.apply { this.mood = mood } ?: MoodDb().apply { this.mood = mood }
    }

    fun setCurrentMoodSleepQuality(sleepQuality: SleepQuality) {
        _currentMood.value = currentMood.value?.apply { this.sleepQuality = sleepQuality }
    }

    fun setCurrentMoodDescription(description: String) {
        _currentMood.value = currentMood.value?.apply { this.description = description }
    }

    fun setCurrentMoodStressLevel(stressLevel: StressLevel) {
        _currentMood.value = currentMood.value?.apply { this.stressLevel = stressLevel }
    }

    fun insertCurrentMood() = try {
        screenModelScope.launch(dispatcherIo) {
            realm.write {
                copyToRealm(currentMood.value ?: return@write)
            }
            resetCurrentMood()
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }


    fun getMoods() = screenModelScope.launch(dispatcherIo) {
        realmMoods.catch {
            _moods.value = UiState.Error(it.message.toString())
        }.collect { result ->
            val moods = result.list.map { mood -> mood }
            _moods.value = UiState.Success(moods)
        }
    }

    fun resetCurrentMood() {
        _currentMood.value = null
    }
}