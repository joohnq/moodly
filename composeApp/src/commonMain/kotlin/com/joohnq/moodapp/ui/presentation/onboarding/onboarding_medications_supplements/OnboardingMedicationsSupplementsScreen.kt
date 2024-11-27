package com.joohnq.moodapp.ui.presentation.onboarding.onboarding_medications_supplements

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.ui.CustomScreen
import com.joohnq.moodapp.ui.presentation.onboarding.onboarding_medications_supplements.event.OnboardingMedicationsSupplementsEvent
import com.joohnq.moodapp.ui.presentation.onboarding.onboarding_medications_supplements.state.OnboardingMedicationSupplementsState
import com.joohnq.moodapp.ui.presentation.onboarding.onboarding_stress_level.OnboardingStressLevelScreen
import com.joohnq.moodapp.ui.presentation.onboarding.OnboardingViewModel

class OnboardingMedicationsSupplementsScreen :
    CustomScreen<OnboardingMedicationSupplementsState>() {
    @Composable
    override fun Screen(): OnboardingMedicationSupplementsState {
        val onboardingViewModel: OnboardingViewModel = sharedViewModel()
        val onboardingState by onboardingViewModel.onboardingState.collectAsState()

        fun onEvent(event: OnboardingMedicationsSupplementsEvent) =
            when (event) {
                OnboardingMedicationsSupplementsEvent.OnNavigateToOnboardingStressLevelScreen ->
                    onNavigate(OnboardingStressLevelScreen())

                OnboardingMedicationsSupplementsEvent.OnGoBack -> onGoBack()
            }

        return OnboardingMedicationSupplementsState(
            selectedOption = onboardingState.medicationsSupplements,
            onAction = onboardingViewModel::onAction,
            onEvent = ::onEvent
        )
    }

    @Composable
    override fun UI(state: OnboardingMedicationSupplementsState) =
        OnboardingMedicationsSupplementsUI(state)
}

