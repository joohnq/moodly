package com.joohnq.onboarding.impl.ui.presentation.onboarding_expression_analysis

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.onboarding.impl.ui.event.OnboardingEvent
import com.joohnq.onboarding.impl.ui.viewmodel.OnboardingContract
import com.joohnq.onboarding.impl.ui.viewmodel.OnboardingViewModel
import com.joohnq.preferences.impl.ui.viewmodel.PreferencesViewModel
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.ui.ObserverSideEffects
import com.joohnq.ui.sharedViewModel
import kotlinx.coroutines.launch

@Composable
fun OnboardingExpressionAnalysisScreen(
    onNavigateToNext: () -> Unit,
    onGoBack: () -> Unit,
    viewModel: OnboardingViewModel = sharedViewModel(),
) {
    val scope = rememberCoroutineScope()
    val snackBarState = rememberSnackBarState()
    val onboardingState by viewModel.state.collectAsState()

    fun onError(message: String) {
        scope.launch {
            snackBarState.showSnackbar(message)
        }
    }

    fun onEvent(event: OnboardingEvent) =
        when (event) {
            OnboardingEvent.AddItems -> {
                viewModel.onIntent(
                    OnboardingContract.Intent.AddItems
                )
            }

            OnboardingEvent.OnGoBack -> onGoBack()
        }

    ObserverSideEffects(
        flow = viewModel.sideEffect,
        onEvent = { effect ->
            when (effect) {
                OnboardingContract.SideEffect.OnboardingCompleted -> onNavigateToNext()
                is OnboardingContract.SideEffect.ShowError -> onError(effect.message)
            }
        }
    )

    OnboardingExpressionAnalysisContent(
        description = onboardingState.moodRecord.description,
        snackBarState = snackBarState,
        onAction = viewModel::onIntent,
        onEvent = ::onEvent
    )
}