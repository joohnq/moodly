package com.joohnq.onboarding.impl.ui.presentation.onboarding_sleep_quality

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.onboarding.impl.event.OnboardingEvent
import com.joohnq.onboarding.impl.viewmodel.OnboardingViewModel
import com.joohnq.ui.sharedViewModel

@Composable
fun OnboardingSleepQualityScreen(
    onNavigateToMedicationsSupplements: () -> Unit,
    onGoBack: () -> Unit
) {
    val onboardingViewModel: com.joohnq.onboarding.impl.ui.viewmodel.OnboardingViewModel = sharedViewModel()
    val onboardingState by onboardingViewModel.state.collectAsState()

    fun onEvent(event: com.joohnq.onboarding.impl.ui.event.OnboardingEvent) =
        when (event) {
            _root_ide_package_.com.joohnq.onboarding.impl.ui.event.OnboardingEvent.OnNavigateToNext -> onNavigateToMedicationsSupplements()
            _root_ide_package_.com.joohnq.onboarding.impl.ui.event.OnboardingEvent.OnGoBack -> onGoBack()
        }

    OnboardingSleepQualityContent(
        state = onboardingState.sleepQuality,
        sliderValue = onboardingState.sliderValue,
        onAction = onboardingViewModel::onIntent,
        onEvent = ::onEvent
    )
}