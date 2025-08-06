package com.joohnq.onboarding.impl.ui.presentation.onboarding_sleep_quality

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.onboarding.impl.ui.event.OnboardingEvent
import com.joohnq.onboarding.impl.ui.viewmodel.OnboardingViewModel
import com.joohnq.ui.sharedViewModel

@Composable
fun OnboardingSleepQualityScreen(
    onNavigateToMedicationsSupplements: () -> Unit,
    onGoBack: () -> Unit,
    viewModel: OnboardingViewModel = sharedViewModel(),
) {
    val state by viewModel.state.collectAsState()

    fun onEvent(event: OnboardingEvent) {
        when (event) {
            OnboardingEvent.NavigateNext -> onNavigateToMedicationsSupplements()
            OnboardingEvent.OnGoBack -> onGoBack()
        }
    }

    OnboardingSleepQualityContent(
        state = state.sleepQuality,
        sliderValue = state.sliderValue,
        onIntent = viewModel::onIntent,
        onEvent = ::onEvent
    )
}
