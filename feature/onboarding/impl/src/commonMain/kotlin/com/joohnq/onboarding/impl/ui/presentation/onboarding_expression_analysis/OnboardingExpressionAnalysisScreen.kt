package com.joohnq.onboarding.impl.ui.presentation.onboarding_expression_analysis

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.onboarding.impl.ui.event.OnboardingEvent
import com.joohnq.onboarding.impl.ui.viewmodel.OnboardingContract
import com.joohnq.onboarding.impl.ui.viewmodel.OnboardingViewModel
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.ui.sharedViewModel

@Composable
fun OnboardingExpressionAnalysisScreen(
    navigateNext: () -> Unit,
    onGoBack: () -> Unit,
    viewModel: OnboardingViewModel = sharedViewModel(),
) {
    val snackBarState = rememberSnackBarState()
    val onboardingState by viewModel.state.collectAsState()

    fun onEvent(event: OnboardingEvent) =
        when (event) {
            OnboardingEvent.NavigateNext -> {
                viewModel.onIntent(
                    OnboardingContract.Intent.AddItems
                )
            }

            OnboardingEvent.OnGoBack -> onGoBack()
        }

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                OnboardingContract.SideEffect.NavigateNext -> navigateNext()
                is OnboardingContract.SideEffect.ShowError ->
                    snackBarState.showSnackbar(sideEffect.message)
            }
        }
    }

    OnboardingExpressionAnalysisContent(
        description = onboardingState.moodRecord.description,
        snackBarState = snackBarState,
        onIntent = viewModel::onIntent,
        onEvent = ::onEvent
    )
}
