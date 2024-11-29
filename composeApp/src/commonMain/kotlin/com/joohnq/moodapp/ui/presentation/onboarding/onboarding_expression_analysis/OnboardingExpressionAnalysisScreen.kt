package com.joohnq.moodapp.ui.presentation.onboarding.onboarding_expression_analysis

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.moodapp.domain.SleepQualityRecord
import com.joohnq.moodapp.domain.StressLevelRecord
import com.joohnq.moodapp.domain.User
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.ui.CustomScreen
import com.joohnq.moodapp.ui.presentation.get_user_name.GetUserNameScreen
import com.joohnq.moodapp.ui.presentation.onboarding.OnboardingIntent
import com.joohnq.moodapp.ui.presentation.onboarding.OnboardingViewModel
import com.joohnq.moodapp.ui.presentation.onboarding.onboarding_expression_analysis.event.OnboardingExpressionEvent
import com.joohnq.moodapp.ui.presentation.onboarding.onboarding_expression_analysis.state.OnboardingExpressionAnalysisState
import com.joohnq.moodapp.ui.state.UiState
import com.joohnq.moodapp.ui.state.UiState.Companion.fold
import com.joohnq.moodapp.viewmodel.SleepQualityIntent
import com.joohnq.moodapp.viewmodel.SleepQualityViewModel
import com.joohnq.moodapp.viewmodel.StatsIntent
import com.joohnq.moodapp.viewmodel.StatsViewModel
import com.joohnq.moodapp.viewmodel.StressLevelIntent
import com.joohnq.moodapp.viewmodel.StressLevelViewModel
import com.joohnq.moodapp.viewmodel.UserIntent
import com.joohnq.moodapp.viewmodel.UserPreferenceIntent
import com.joohnq.moodapp.viewmodel.UserPreferenceViewModel
import com.joohnq.moodapp.viewmodel.UserViewModel
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
        val onboardingState by onboardingViewModel.onboardingState.collectAsState()
        val userState by userViewModel.userState.collectAsState()
        val statsState by statsViewModel.statsState.collectAsState()
        val sleepQualityState by sleepQualityViewModel.sleepQualityState.collectAsState()
        val stressLevelState by stressLevelViewModel.stressLevelState.collectAsState()
        val userPreferencesState by userPreferencesViewModel.userPreferencesState.collectAsState()

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
                        UserIntent.UpdateUser(
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
                    userPreferencesViewModel.onAction(UserPreferenceIntent.UpdateSkipOnboardingScreen())
                    sleepQualityViewModel.onAction(SleepQualityIntent.ResetAddingStatus)
                    stressLevelViewModel.onAction(StressLevelIntent.ResetAddingStatus)
                    statsViewModel.onAction(StatsIntent.ResetAddingStatus)
                    userViewModel.onAction(UserIntent.ResetUpdatingStatus)
                    onboardingViewModel.onAction(OnboardingIntent.ResetStatsRecord)
                },
                onAnyHasError = {
                    scope.launch { snackBarState.showSnackbar(it) }
                }
            )
        }

        LaunchedEffect(userPreferencesState.updating) {
            userPreferencesState.updating.fold(
                onSuccess = {
                    onNavigate(GetUserNameScreen(), true)
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


