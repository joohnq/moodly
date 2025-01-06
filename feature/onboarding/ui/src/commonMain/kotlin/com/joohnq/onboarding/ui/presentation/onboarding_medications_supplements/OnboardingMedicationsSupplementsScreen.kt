package com.joohnq.onboarding.ui.presentation.onboarding_medications_supplements

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.core.ui.CustomScreen
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.onboarding.ui.event.OnboardingEvent
import com.joohnq.onboarding.ui.presentation.onboarding_medications_supplements.state.OnboardingMedicationsSupplementsState
import com.joohnq.onboarding.ui.viewmodel.OnboardingViewModel

class OnboardingMedicationsSupplementsScreen(
    private val onNavigateToStressLevel: () -> Unit,
    private val onGoBack: () -> Unit,
) :
    CustomScreen<OnboardingMedicationsSupplementsState>() {
    @Composable
    override fun Screen(): OnboardingMedicationsSupplementsState {
        val onboardingViewModel: OnboardingViewModel = sharedViewModel()
        val onboardingState by onboardingViewModel.state.collectAsState()

        fun onEvent(event: OnboardingEvent) =
            when (event) {
                OnboardingEvent.OnNavigateToNext ->
                    onNavigateToStressLevel()

                OnboardingEvent.OnGoBack ->
                    onGoBack()
            }

        return OnboardingMedicationsSupplementsState(
            selectedOption = onboardingState.medicationsSupplements,
            onAction = onboardingViewModel::onAction,
            onEvent = ::onEvent
        )
    }

    @Composable
    override fun UI(state: OnboardingMedicationsSupplementsState) =
        OnboardingMedicationsSupplementsUI(state)
}

