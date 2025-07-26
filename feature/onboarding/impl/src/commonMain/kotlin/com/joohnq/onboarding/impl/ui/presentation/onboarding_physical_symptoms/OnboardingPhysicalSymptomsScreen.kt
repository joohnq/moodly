package com.joohnq.onboarding.impl.ui.presentation.onboarding_physical_symptoms

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.onboarding.impl.event.OnboardingEvent
import com.joohnq.onboarding.impl.viewmodel.OnboardingViewModel
import com.joohnq.ui.sharedViewModel

@Composable
fun OnboardingPhysicalSymptomsScreen(
    onNavigateToSleepQuality: () -> Unit,
    onGoBack: () -> Unit
) {
    val onboardingViewModel: com.joohnq.onboarding.impl.ui.viewmodel.OnboardingViewModel = sharedViewModel()
    val onboardingState by onboardingViewModel.state.collectAsState()

    fun onEvent(event: com.joohnq.onboarding.impl.ui.event.OnboardingEvent) =
        when (event) {
            _root_ide_package_.com.joohnq.onboarding.impl.ui.event.OnboardingEvent.OnNavigateToNext -> onNavigateToSleepQuality()
            _root_ide_package_.com.joohnq.onboarding.impl.ui.event.OnboardingEvent.OnGoBack -> onGoBack()
        }

    OnboardingPhysicalSymptomsContent(
        state = onboardingState.physicalSymptoms,
        onAction = onboardingViewModel::onIntent,
        onEvent = ::onEvent
    )
}