package com.joohnq.onboarding.impl.ui.presentation.onboarding_professional_help

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.onboarding.impl.ui.event.OnboardingEvent
import com.joohnq.onboarding.impl.ui.viewmodel.OnboardingViewModel
import com.joohnq.ui.sharedViewModel

@Composable
fun OnboardingProfessionalHelpScreen(
    onNavigateToPhysicalSymptoms: () -> Unit,
    onGoBack: () -> Unit,
    viewModel: OnboardingViewModel =
        sharedViewModel(),
) {
    val state by viewModel.state.collectAsState()

    fun onEvent(event: OnboardingEvent) =
        when (event) {
            OnboardingEvent.AddItems -> onNavigateToPhysicalSymptoms()
            OnboardingEvent.OnGoBack -> onGoBack()
        }

    OnboardingProfessionalHelpContent(
        state = state.soughtHelp,
        onAction = viewModel::onIntent,
        onEvent = ::onEvent
    )
}