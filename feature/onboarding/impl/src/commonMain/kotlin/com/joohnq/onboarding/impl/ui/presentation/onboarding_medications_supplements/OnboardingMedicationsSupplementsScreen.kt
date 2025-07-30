package com.joohnq.onboarding.impl.ui.presentation.onboarding_medications_supplements

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.onboarding.impl.ui.event.OnboardingEvent
import com.joohnq.onboarding.impl.ui.viewmodel.OnboardingViewModel
import com.joohnq.ui.sharedViewModel

@Composable
fun OnboardingMedicationsSupplementsScreen(
    onNavigateToStressLevel: () -> Unit,
    onGoBack: () -> Unit,
    viewModel: OnboardingViewModel = sharedViewModel(),
) {
    val state by viewModel.state.collectAsState()

    fun onEvent(event: OnboardingEvent) =
        when (event) {
            OnboardingEvent.AddItems ->
                onNavigateToStressLevel()

            OnboardingEvent.OnGoBack ->
                onGoBack()
        }

    OnboardingMedicationsSupplementsContent(
        state = state.medicationsSupplements,
        onAction = viewModel::onIntent,
        onEvent = ::onEvent
    )
}