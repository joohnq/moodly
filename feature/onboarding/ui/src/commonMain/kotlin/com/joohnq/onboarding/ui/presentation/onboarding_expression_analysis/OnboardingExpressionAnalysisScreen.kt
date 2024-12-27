package com.joohnq.onboarding.ui.presentation.onboarding_expression_analysis

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.domain.entity.User
import com.joohnq.mood.CustomScreen
import com.joohnq.mood.sharedViewModel
import com.joohnq.mood.state.UiState
import com.joohnq.mood.state.UiState.Companion.fold
import com.joohnq.mood.ui.viewmodel.StatsIntent
import com.joohnq.mood.ui.viewmodel.StatsViewModel
import com.joohnq.onboarding.ui.presentation.onboarding_expression_analysis.event.OnboardingExpressionEvent
import com.joohnq.onboarding.ui.presentation.onboarding_expression_analysis.state.OnboardingExpressionAnalysisState
import com.joohnq.onboarding.ui.viewmodel.OnboardingViewModel
import com.joohnq.onboarding.ui.viewmodel.OnboardingViewModelIntent
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.joohnq.sleep_quality.ui.viewmodel.SleepQualityIntent
import com.joohnq.sleep_quality.ui.viewmodel.SleepQualityViewModel
import com.joohnq.stress_level.domain.entity.StressLevelRecord
import com.joohnq.stress_level.ui.viewmodel.StressLevelIntent
import com.joohnq.stress_level.ui.viewmodel.StressLevelViewModel
import com.joohnq.user.ui.viewmodel.UserViewModelIntent
import com.joohnq.user.ui.viewmodel.UserPreferenceViewModelIntent
import com.joohnq.user.ui.viewmodel.UserPreferenceViewModel
import com.joohnq.user.ui.viewmodel.UserViewModel
import kotlinx.coroutines.launch

class OnboardingExpressionAnalysisScreen : CustomScreen<OnboardingExpressionAnalysisState>() {
    @Composable
    override fun Screen(): OnboardingExpressionAnalysisState {
        val onboardingViewModel: OnboardingViewModel = sharedViewModel()
        val userViewModel: UserViewModel = sharedViewModel()
        val statsViewModel: StatsViewModel = sharedViewModel()
        val sleepQualityViewModel: SleepQualityViewModel = sharedViewModel()
        val stressLevelViewModel: StressLevelViewModel = sharedViewModel()
        val userPreferencesViewModel: UserPreferenceViewModel = sharedViewModel()
        val scope = rememberCoroutineScope()
        val snackBarState = remember { SnackbarHostState() }
        val onboardingState by onboardingViewModel.state.collectAsState()
        val userState by userViewModel.state.collectAsState()
        val statsState by statsViewModel.statsState.collectAsState()
        val sleepQualityState by sleepQualityViewModel.sleepQualityState.collectAsState()
        val stressLevelState by stressLevelViewModel.stressLevelState.collectAsState()
        val userPreferencesState by userPreferencesViewModel.state.collectAsState()

        fun onEvent(event: OnboardingExpressionEvent) =
            when (event) {
                OnboardingExpressionEvent.OnContinue -> {
                    sleepQualityViewModel.onAction(
                        SleepQualityIntent.AddSleepQualityRecord(
                            SleepQualityRecord.Builder()
                                .setSleepQuality(sleepQuality = onboardingState.sleepQuality)
                                .build()
                        )
                    )
                    stressLevelViewModel.onAction(
                        StressLevelIntent.AddStressLevelRecord(
                            StressLevelRecord.Builder()
                                .setStressLevel(onboardingState.stressLevel)
                                .build()
                        )
                    )
                    statsViewModel.onAction(StatsIntent.AddStatsRecord(onboardingState.statsRecord))
                    userViewModel.onAction(
                        UserViewModelIntent.UpdateUser(
                            User.init().copy(
                                physicalSymptoms = onboardingState.physicalSymptoms!!,
                                medicationsSupplements = onboardingState.medicationsSupplements!!,
                                soughtHelp = onboardingState.soughtHelp!!
                            )
                        )
                    )
                }

                OnboardingExpressionEvent.OnGoBack -> onGoBack()
            }

        LaunchedEffect(
            stressLevelState.adding,
            sleepQualityState.adding,
            statsState.adding,
            userState.updating
        ) {
            UiState.fold(
                stressLevelState.adding,
                sleepQualityState.adding,
                statsState.adding,
                userState.updating,
                onAllSuccess = {
                    userPreferencesViewModel.onAction(UserPreferenceViewModelIntent.UpdateSkipOnboardingScreen())
                    sleepQualityViewModel.onAction(SleepQualityIntent.ResetAddingStatus)
                    stressLevelViewModel.onAction(StressLevelIntent.ResetAddingStatus)
                    statsViewModel.onAction(StatsIntent.ResetAddingStatus)
                    userViewModel.onAction(UserViewModelIntent.ResetUpdatingStatus)
                    onboardingViewModel.onAction(OnboardingViewModelIntent.ResetStatsRecord)
                },
                onAnyHasError = {
                    scope.launch { snackBarState.showSnackbar(it) }
                }
            )
        }

        LaunchedEffect(userPreferencesState.updating) {
            userPreferencesState.updating.fold(
                onSuccess = {
//                    onNavigate(GetUserNameScreen(), true)
                },
                onError = {
                    scope.launch { snackBarState.showSnackbar(it) }
                }
            )
        }

        return OnboardingExpressionAnalysisState(
            desc = onboardingState.statsRecord.description,
            snackBarState = snackBarState,
            onAction = onboardingViewModel::onAction,
            onEvent = ::onEvent
        )
    }

    @Composable
    override fun UI(state: OnboardingExpressionAnalysisState) =
        OnboardingExpressionAnalysisUI(state)

    object OnboardingExpressionTestTag {
        const val TEXT_INPUT = "TEXT_INPUT"
    }
}


