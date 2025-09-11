package com.joohnq.onboarding.impl.ui.presentation.onboarding_medications_supplements

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.joohnq.onboarding.impl.ui.event.OnboardingEvent
import com.joohnq.onboarding.impl.ui.viewmodel.OnboardingViewModel
import com.joohnq.ui.sharedViewModel

@Composable
fun OnboardingMedicationsSupplementsScreen(
    onNavigateToStressLevel: () -> Unit,
    onGoBack: () -> Unit,
    viewModel: OnboardingViewModel = sharedViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    fun onEvent(event: OnboardingEvent) {
        when (event) {
            OnboardingEvent.NavigateNext -> onNavigateToStressLevel()
            OnboardingEvent.OnGoBack -> onGoBack()
        }
    }

    OnboardingMedicationsSupplementsContent(
        state = state.medicationsSupplements,
        onIntent = viewModel::onIntent,
        onEvent = ::onEvent
    )
}
