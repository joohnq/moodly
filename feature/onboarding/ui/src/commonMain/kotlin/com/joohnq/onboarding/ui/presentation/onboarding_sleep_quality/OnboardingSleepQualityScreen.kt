package com.joohnq.onboarding.ui.presentation.onboarding_sleep_quality

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.onboarding.ui.event.OnboardingEvent
import com.joohnq.onboarding.ui.viewmodel.OnboardingViewModel

@Composable
fun OnboardingSleepQualityScreen(
    onNavigateToMedicationsSupplements: () -> Unit,
    onGoBack: () -> Unit,
) {
    val onboardingViewModel: OnboardingViewModel = sharedViewModel()
    val onboardingState by onboardingViewModel.state.collectAsState()

    fun onEvent(event: OnboardingEvent) =
        when (event) {
            OnboardingEvent.OnNavigateToNext -> onNavigateToMedicationsSupplements()
            OnboardingEvent.OnGoBack -> onGoBack()
        }

    OnboardingSleepQualityUI(
        sliderValue = onboardingState.sliderValue,
        onAction = onboardingViewModel::onAction,
        onEvent = ::onEvent
    )
}
