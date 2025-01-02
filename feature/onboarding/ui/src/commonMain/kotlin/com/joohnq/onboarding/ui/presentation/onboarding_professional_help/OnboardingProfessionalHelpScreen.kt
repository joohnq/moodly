package com.joohnq.onboarding.ui.presentation.onboarding_professional_help

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.onboarding.ui.event.OnboardingEvent
import com.joohnq.onboarding.ui.presentation.onboarding_professional_help.state.OnboardingProfessionalHelpState
import com.joohnq.onboarding.ui.viewmodel.OnboardingViewModel
import com.joohnq.shared.ui.CustomScreen
import com.joohnq.shared.ui.sharedViewModel

class OnboardingProfessionalHelpScreen : CustomScreen<OnboardingProfessionalHelpState>() {
    @Composable
    override fun Screen(): OnboardingProfessionalHelpState {
        val onboardingViewModel: OnboardingViewModel = sharedViewModel()
        val onboardingState by onboardingViewModel.state.collectAsState()

        fun onEvent(event: OnboardingEvent) =
            when (event) {
                OnboardingEvent.OnNavigateToNext -> {}
//                    onNavigate(OnboardingPhysicalSymptomsScreen())

                OnboardingEvent.OnGoBack -> {}
//                    onGoBack()
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


