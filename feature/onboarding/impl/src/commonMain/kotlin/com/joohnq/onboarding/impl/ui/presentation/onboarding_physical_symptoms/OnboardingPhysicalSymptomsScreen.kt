package com.joohnq.onboarding.impl.ui.presentation.onboarding_physical_symptoms

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.onboarding.impl.ui.event.OnboardingEvent
import com.joohnq.onboarding.impl.ui.viewmodel.OnboardingViewModel
import com.joohnq.ui.sharedViewModel

@Composable
fun OnboardingPhysicalSymptomsScreen(
    onNavigateToSleepQuality: () -> Unit,
    onGoBack: () -> Unit,
    viewModel: OnboardingViewModel = sharedViewModel(),
) {
    val state by viewModel.state.collectAsState()

    fun onEvent(event: OnboardingEvent) =
        when (event) {
            OnboardingEvent.NavigateNext -> onNavigateToSleepQuality()
            OnboardingEvent.OnGoBack -> onGoBack()
        }

    OnboardingPhysicalSymptomsContent(
        state = state.physicalSymptoms,
        onIntent = viewModel::onIntent,
        onEvent = ::onEvent
    )
}
