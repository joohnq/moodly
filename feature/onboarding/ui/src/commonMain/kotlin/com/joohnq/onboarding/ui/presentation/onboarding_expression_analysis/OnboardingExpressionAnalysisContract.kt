package com.joohnq.onboarding.ui.presentation.onboarding_expression_analysis

import com.joohnq.domain.entity.UiState
import com.joohnq.mood.domain.entity.MoodRecord
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.joohnq.stress_level.domain.entity.StressLevelRecord

sealed interface OnboardingExpressionAnalysisContract {
    sealed interface Event : OnboardingExpressionAnalysisContract {
    }

    sealed interface Intent : OnboardingExpressionAnalysisContract {
        data class AddAll(
            val mood: MoodRecord,
            val stressLevel: StressLevelRecord,
            val sleepQuality: SleepQualityRecord
        ) : Intent
    }

    sealed interface SideEffect : OnboardingExpressionAnalysisContract {
        data class ShowError(val error: Throwable) : SideEffect
    }

    data class State(
        val status: UiState<Unit> = UiState.Idle
    ) : OnboardingExpressionAnalysisContract
}