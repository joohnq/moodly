package com.joohnq.onboarding.ui.presentation.onboarding_expression_analysis

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import com.diamondedge.logging.logging
import com.joohnq.domain.entity.UiState
import com.joohnq.mood.ui.resource.mapper.toDomain
import com.joohnq.onboarding.ui.viewmodel.OnboardingContract
import com.joohnq.onboarding.ui.viewmodel.OnboardingViewModel
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.sleep_quality.ui.resource.mapper.toDomain
import com.joohnq.stress_level.ui.resource.mapper.toDomain
import com.joohnq.ui.sharedViewModel
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun OnboardingExpressionAnalysisScreen(
    onNavigateToNext: () -> Unit,
    onGoBack: () -> Unit,
) {
    val viewModel: OnboardingViewModel = sharedViewModel()
    val scope = rememberCoroutineScope()
    val snackBarState = rememberSnackBarState()
    val onboardingState by viewModel.state.collectAsState()
    val onboardingExpressionAnalysisViewModel: OnboardingExpressionAnalysisViewModel =
        koinViewModel()
    val state by onboardingExpressionAnalysisViewModel.state.collectAsState()

    fun onError(message: String) {
        scope.launch {
            snackBarState.showSnackbar(message)
        }
    }

    fun onEvent(event: OnboardingContract.Event) =
        when (event) {
            OnboardingContract.Event.OnContinue -> onboardingExpressionAnalysisViewModel.onIntent(
                OnboardingExpressionAnalysisContract.Intent.AddAll(
                    onboardingState.moodRecord.toDomain().copy(id = 1),
                    onboardingState.stressLevel.toDomain().copy(id = 1),
                    onboardingState.sleepQuality.toDomain().copy(id = 1)
                )
            )

            OnboardingContract.Event.GoBack -> onGoBack()
        }

    LaunchedEffect(state.status) {
        when (val status = state.status) {
            is UiState.Success -> onNavigateToNext()
            is UiState.Error -> onError(status.error.message.toString())
            else -> logging("KMLogging Tag").i { "OnboardingExpressionAnalysisScreen: $status" }
        }
    }

    OnboardingExpressionAnalysisUI(
        description = onboardingState.moodRecord.description,
        snackBarState = snackBarState,
        onIntent = viewModel::onIntent,
        onEvent = ::onEvent
    )
}
