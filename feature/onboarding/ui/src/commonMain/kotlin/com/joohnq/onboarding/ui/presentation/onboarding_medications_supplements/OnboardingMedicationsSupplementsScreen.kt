package com.joohnq.onboarding.ui.presentation.onboarding_medications_supplements

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.onboarding.ui.presentation.onboarding_medications_supplements.event.OnboardingMedicationsSupplementsEvent
import com.joohnq.onboarding.ui.presentation.onboarding_medications_supplements.state.OnboardingMedicationsSupplementsState
import com.joohnq.onboarding.ui.viewmodel.OnboardingViewModel
import com.joohnq.shared.ui.CustomScreen
import com.joohnq.shared.ui.sharedViewModel

class OnboardingMedicationsSupplementsScreen :
    CustomScreen<OnboardingMedicationsSupplementsState>() {
    @Composable
    override fun Screen(): OnboardingMedicationsSupplementsState {
        val onboardingViewModel: OnboardingViewModel = sharedViewModel()
        val onboardingState by onboardingViewModel.state.collectAsState()

        fun onEvent(event: OnboardingMedicationsSupplementsEvent) =
            when (event) {
                OnboardingMedicationsSupplementsEvent.OnNavigateToOnboardingStressLevelScreen -> {}
//                    onNavigate(OnboardingStressLevelScreen())

                OnboardingMedicationsSupplementsEvent.OnGoBack -> {}
//                    onGoBack()
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

