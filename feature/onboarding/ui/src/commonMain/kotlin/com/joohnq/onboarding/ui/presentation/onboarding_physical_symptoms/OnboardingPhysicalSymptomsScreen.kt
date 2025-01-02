package com.joohnq.onboarding.ui.presentation.onboarding_physical_symptoms

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.onboarding.ui.event.OnboardingEvent
import com.joohnq.onboarding.ui.presentation.onboarding_physical_symptoms.state.OnboardingPhysicalSymptomsState
import com.joohnq.onboarding.ui.viewmodel.OnboardingViewModel
import com.joohnq.shared.ui.CustomScreen
import com.joohnq.shared.ui.sharedViewModel

class OnboardingPhysicalSymptomsScreen : CustomScreen<OnboardingPhysicalSymptomsState>() {
    @Composable
    override fun Screen(): OnboardingPhysicalSymptomsState {
        val onboardingViewModel: OnboardingViewModel = sharedViewModel()
        val onboardingState by onboardingViewModel.state.collectAsState()

        fun onEvent(event: OnboardingEvent) =
            when (event) {
                OnboardingEvent.OnNavigateToNext -> {}
//                    onNavigate(OnboardingSleepQualityScreen())

                OnboardingEvent.OnGoBack -> {}
//                    onGoBack()
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

