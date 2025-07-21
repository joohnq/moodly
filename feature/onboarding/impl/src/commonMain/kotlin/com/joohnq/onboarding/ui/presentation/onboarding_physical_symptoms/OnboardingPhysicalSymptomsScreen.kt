package com.joohnq.onboarding.ui.presentation.onboarding_physical_symptoms

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.ui.sharedViewModel
import com.joohnq.onboarding.ui.event.OnboardingEvent
import com.joohnq.onboarding.ui.viewmodel.OnboardingViewModel

@Composable
fun OnboardingPhysicalSymptomsScreen(
    onNavigateToSleepQuality: () -> Unit,
    onGoBack: () -> Unit,
) {
    val onboardingViewModel: OnboardingViewModel = sharedViewModel()
    val onboardingState by onboardingViewModel.state.collectAsState()

    fun onEvent(event: OnboardingEvent) =
        when (event) {
            OnboardingEvent.OnNavigateToNext -> onNavigateToSleepQuality()
            OnboardingEvent.OnGoBack -> onGoBack()
        }

    OnboardingPhysicalSymptomsUI(
        physicalSymptoms = onboardingState.physicalSymptoms,
        onAction = onboardingViewModel::onAction,
        onEvent = ::onEvent
    )
}

