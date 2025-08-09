package com.joohnq.onboarding.impl.ui.presentation.onboarding_expression_analysis

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.joohnq.onboarding.impl.ui.event.OnboardingEvent
import com.joohnq.onboarding.impl.ui.viewmodel.OnboardingContract
import com.joohnq.onboarding.impl.ui.viewmodel.OnboardingViewModel
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.ui.observe
import com.joohnq.ui.sharedViewModel
import kotlinx.coroutines.launch

@Composable
fun OnboardingExpressionAnalysisScreen(
    navigateNext: () -> Unit,
    onGoBack: () -> Unit,
    viewModel: OnboardingViewModel = sharedViewModel(),
) {
    val snackBarState = rememberSnackBarState()
    val (state, onIntent) =
        viewModel.observe { sideEffect ->
            when (sideEffect) {
                OnboardingContract.SideEffect.NavigateNext -> navigateNext()
                is OnboardingContract.SideEffect.ShowError ->
                    launch { snackBarState.showSnackbar(sideEffect.message) }
            }
        }

    fun onEvent(event: OnboardingEvent) {
        when (event) {
            OnboardingEvent.NavigateNext -> {
                viewModel.onIntent(
                    OnboardingContract.Intent.AddItems
                )
            }

            OnboardingEvent.OnGoBack -> onGoBack()
        }
    }

    OnboardingExpressionAnalysisContent(
        description = state.moodRecord.description,
        snackBarState = snackBarState,
        onIntent = onIntent,
        onEvent = ::onEvent
    )
}
