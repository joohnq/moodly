package com.joohnq.onboarding.ui.presentation.onboarding_expression_analysis

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.domain.entity.UiState
import com.joohnq.mood.domain.entity.MoodRecord
import com.joohnq.mood.domain.use_case.AddMoodUseCase
import com.joohnq.preferences.domain.use_case.UpdateSkipOnboardingUseCase
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.joohnq.sleep_quality.domain.use_case.AddSleepQualityUseCase
import com.joohnq.stress_level.domain.entity.StressLevelRecord
import com.joohnq.stress_level.domain.use_case.AddStressLevelUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OnboardingExpressionAnalysisViewModel(
    private val addMoodUseCase: AddMoodUseCase,
    private val addSleepQualityUseCase: AddSleepQualityUseCase,
    private val addStressLevelUseCase: AddStressLevelUseCase,
    private val updateSkipOnboardingUseCase: UpdateSkipOnboardingUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(OnboardingExpressionAnalysisContract.State())
    val state: StateFlow<OnboardingExpressionAnalysisContract.State> = _state.asStateFlow()
    private val _sideEffect =
        Channel<OnboardingExpressionAnalysisContract.SideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    fun onIntent(intent: OnboardingExpressionAnalysisContract.Intent) {
        when (intent) {
            is OnboardingExpressionAnalysisContract.Intent.AddAll -> addAll(
                intent.mood,
                intent.stressLevel,
                intent.sleepQuality,
            )
        }
    }

    private fun addAll(
        mood: MoodRecord,
        stressLevel: StressLevelRecord,
        sleepQuality: SleepQualityRecord,
    ) = viewModelScope.launch {
        _state.update { it.copy(status = UiState.Loading) }
        try {
            coroutineScope {
                awaitAll(
                    async { addMoodUseCase(mood).getOrThrow() },
                    async { addSleepQualityUseCase(sleepQuality).getOrThrow() },
                    async { addStressLevelUseCase(stressLevel).getOrThrow() }
                )
            }
            updateSkipOnboardingUseCase(true)
            _state.update { it.copy(status = UiState.Success(Unit)) }
        } catch (e: Exception) {
            _state.update { it.copy(status = UiState.Error(e)) }
        }
    }
}