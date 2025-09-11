package com.joohnq.onboarding.impl.ui.presentation.onboarding_professional_help

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.joohnq.onboarding.impl.ui.event.OnboardingEvent
import com.joohnq.onboarding.impl.ui.viewmodel.OnboardingViewModel
import com.joohnq.ui.sharedViewModel

@Composable
fun OnboardingProfessionalHelpScreen(
    onNavigateToPhysicalSymptoms: () -> Unit,
    onGoBack: () -> Unit,
    viewModel: OnboardingViewModel = sharedViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    fun onEvent(event: OnboardingEvent) {
        when (event) {
            OnboardingEvent.NavigateNext -> onNavigateToPhysicalSymptoms()
            OnboardingEvent.OnGoBack -> onGoBack()
        }
    }

    OnboardingProfessionalHelpContent(
        state = state.soughtHelp,
        onIntent = viewModel::onIntent,
        onEvent = ::onEvent
    )
}
