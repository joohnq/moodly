package com.joohnq.onboarding.impl.presentation.onboarding_professional_help

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.onboarding.impl.event.OnboardingEvent
import com.joohnq.onboarding.impl.viewmodel.OnboardingViewModel
import com.joohnq.ui.sharedViewModel

@Composable
fun OnboardingProfessionalHelpScreen(
    onNavigateToPhysicalSymptoms: () -> Unit,
    onGoBack: () -> Unit
) {
    val onboardingViewModel: OnboardingViewModel = sharedViewModel()
    val onboardingState by onboardingViewModel.state.collectAsState()

    fun onEvent(event: OnboardingEvent) =
        when (event) {
            OnboardingEvent.OnNavigateToNext -> onNavigateToPhysicalSymptoms()
            OnboardingEvent.OnGoBack -> onGoBack()
        }

    OnboardingProfessionalHelpContent(
        state = onboardingState.soughtHelp,
        onAction = onboardingViewModel::onIntent,
        onEvent = ::onEvent
    )
}
