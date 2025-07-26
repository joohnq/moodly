package com.joohnq.onboarding.impl.presentation.onboarding_medications_supplements

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.onboarding.impl.event.OnboardingEvent
import com.joohnq.onboarding.impl.viewmodel.OnboardingViewModel
import com.joohnq.ui.sharedViewModel

@Composable
fun OnboardingMedicationsSupplementsScreen(
    onNavigateToStressLevel: () -> Unit,
    onGoBack: () -> Unit
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

    OnboardingMedicationsSupplementsContent(
        state = state.medicationsSupplements,
        onAction = onboardingViewModel::onIntent,
        onEvent = ::onEvent
    )
}
