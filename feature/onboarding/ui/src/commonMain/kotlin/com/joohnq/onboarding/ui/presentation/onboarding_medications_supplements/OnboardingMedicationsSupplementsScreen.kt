package com.joohnq.onboarding.ui.presentation.onboarding_medications_supplements

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.onboarding.ui.event.OnboardingEvent
import com.joohnq.onboarding.ui.viewmodel.OnboardingViewModel

@Composable
fun OnboardingMedicationsSupplementsScreen(
    onNavigateToStressLevel: () -> Unit,
    onGoBack: () -> Unit,
) {
    val onboardingViewModel: OnboardingViewModel = sharedViewModel()
    val state by onboardingViewModel.state.collectAsState()

    fun onEvent(event: OnboardingEvent) =
        when (event) {
            OnboardingEvent.OnNavigateToNext ->
                onNavigateToStressLevel()

            OnboardingEvent.OnGoBack ->
                onGoBack()
        }

    OnboardingMedicationsSupplementsUI(
        state = state,
        onAction = onboardingViewModel::onAction,
        onEvent = ::onEvent
    )
}

