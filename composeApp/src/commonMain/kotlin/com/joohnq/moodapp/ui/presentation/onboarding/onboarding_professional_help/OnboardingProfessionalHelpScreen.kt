package com.joohnq.moodapp.ui.presentation.onboarding.onboarding_professional_help

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.ui.CustomScreen
import com.joohnq.moodapp.ui.presentation.onboarding.onboarding_physical_symptoms.OnboardingPhysicalSymptomsScreen
import com.joohnq.moodapp.ui.presentation.onboarding.onboarding_professional_help.event.OnboardingProfessionalHelpEvent
import com.joohnq.moodapp.ui.presentation.onboarding.onboarding_professional_help.state.OnboardingProfessionalHelpState
import com.joohnq.moodapp.viewmodel.OnboardingViewModel

class OnboardingProfessionalHelpScreen : CustomScreen<OnboardingProfessionalHelpState>() {
    @Composable
    override fun Screen(): OnboardingProfessionalHelpState {
        val onboardingViewModel: OnboardingViewModel = sharedViewModel()
        val onboardingState by onboardingViewModel.onboardingState.collectAsState()

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


