package com.joohnq.onboarding.ui.presentation.onboarding_professional_help

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.ui.sharedViewModel
import com.joohnq.onboarding.ui.event.OnboardingEvent
import com.joohnq.onboarding.ui.viewmodel.OnboardingViewModel

@Composable
fun OnboardingProfessionalHelpScreen(
    onNavigateToPhysicalSymptoms: () -> Unit,
    onGoBack: () -> Unit,
) {
    val onboardingViewModel: OnboardingViewModel = sharedViewModel()
    val onboardingState by onboardingViewModel.state.collectAsState()

    fun onEvent(event: OnboardingEvent) =
        when (event) {
            OnboardingEvent.OnNavigateToNext -> onNavigateToPhysicalSymptoms()
            OnboardingEvent.OnGoBack -> onGoBack()
        }

    OnboardingProfessionalHelpUI(
        soughtHelp = onboardingState.soughtHelp,
        onAction = onboardingViewModel::onAction,
        onEvent = ::onEvent
    )
}


