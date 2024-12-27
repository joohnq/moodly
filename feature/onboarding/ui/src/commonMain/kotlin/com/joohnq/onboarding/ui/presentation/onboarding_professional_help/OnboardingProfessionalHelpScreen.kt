package com.joohnq.onboarding.ui.presentation.onboarding_professional_help

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.mood.CustomScreen
import com.joohnq.mood.sharedViewModel
import com.joohnq.onboarding.ui.presentation.onboarding_physical_symptoms.OnboardingPhysicalSymptomsScreen
import com.joohnq.onboarding.ui.presentation.onboarding_professional_help.event.OnboardingProfessionalHelpEvent
import com.joohnq.onboarding.ui.presentation.onboarding_professional_help.state.OnboardingProfessionalHelpState
import com.joohnq.onboarding.ui.viewmodel.OnboardingViewModel

class OnboardingProfessionalHelpScreen : CustomScreen<OnboardingProfessionalHelpState>() {
    @Composable
    override fun Screen(): OnboardingProfessionalHelpState {
        val onboardingViewModel: OnboardingViewModel = sharedViewModel()
        val onboardingState by onboardingViewModel.state.collectAsState()

        fun onEvent(event: OnboardingProfessionalHelpEvent) =
            when (event) {
                OnboardingProfessionalHelpEvent.OnNavigateToOnboardingPhysicalSymptomsScreen ->
                    onNavigate(OnboardingPhysicalSymptomsScreen())

                OnboardingProfessionalHelpEvent.OnGoBack -> onGoBack()
            }

        return OnboardingProfessionalHelpState(
            selectedOption = onboardingState.soughtHelp,
            onAction = onboardingViewModel::onAction,
            onEvent = ::onEvent
        )
    }

    @Composable
    override fun UI(state: OnboardingProfessionalHelpState) = OnboardingProfessionalHelpUI(state)
}


