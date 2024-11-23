package com.joohnq.moodapp.ui.presentation.onboarding.onboarding_physical_symptoms

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.ui.CustomScreen
import com.joohnq.moodapp.ui.presentation.onboarding.onboarding_physical_symptoms.event.OnboardingPhysicalSymptomsEvent
import com.joohnq.moodapp.ui.presentation.onboarding.onboarding_physical_symptoms.state.OnboardingPhysicalSymptomsState
import com.joohnq.moodapp.ui.presentation.onboarding.onboarding_sleep_quality.OnboardingSleepQualityScreen
import com.joohnq.moodapp.viewmodel.OnboardingViewModel

class OnboardingPhysicalSymptomsScreen : CustomScreen<OnboardingPhysicalSymptomsState>() {
    @Composable
    override fun Screen(): OnboardingPhysicalSymptomsState {
        val onboardingViewModel: OnboardingViewModel = sharedViewModel()
        val onboardingState by onboardingViewModel.onboardingState.collectAsState()

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

