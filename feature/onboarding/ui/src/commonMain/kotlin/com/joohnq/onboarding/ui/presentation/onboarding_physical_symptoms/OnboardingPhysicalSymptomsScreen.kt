package com.joohnq.onboarding.ui.presentation.onboarding_physical_symptoms

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.mood.CustomScreen
import com.joohnq.mood.sharedViewModel
import com.joohnq.onboarding.ui.presentation.onboarding_physical_symptoms.event.OnboardingPhysicalSymptomsEvent
import com.joohnq.onboarding.ui.presentation.onboarding_physical_symptoms.state.OnboardingPhysicalSymptomsState
import com.joohnq.onboarding.ui.presentation.onboarding_sleep_quality.OnboardingSleepQualityScreen
import com.joohnq.onboarding.ui.viewmodel.OnboardingViewModel

class OnboardingPhysicalSymptomsScreen : CustomScreen<OnboardingPhysicalSymptomsState>() {
    @Composable
    override fun Screen(): OnboardingPhysicalSymptomsState {
        val onboardingViewModel: OnboardingViewModel = sharedViewModel()
        val onboardingState by onboardingViewModel.state.collectAsState()

        fun onEvent(event: OnboardingPhysicalSymptomsEvent) =
            when (event) {
                OnboardingPhysicalSymptomsEvent.OnNavigateOnboardingSleepQualityScreen ->
                    onNavigate(OnboardingSleepQualityScreen())

                OnboardingPhysicalSymptomsEvent.OnGoBack -> onGoBack()
            }

        return OnboardingPhysicalSymptomsState(
            selectedOption = onboardingState.physicalSymptoms,
            onAction = onboardingViewModel::onAction,
            onEvent = ::onEvent
        )
    }

    @Composable
    override fun UI(state: OnboardingPhysicalSymptomsState) = OnboardingPhysicalSymptomsUI(state)
}

