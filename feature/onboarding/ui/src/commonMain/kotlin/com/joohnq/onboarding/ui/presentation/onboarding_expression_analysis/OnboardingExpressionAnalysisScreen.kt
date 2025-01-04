package com.joohnq.onboarding.ui.presentation.onboarding_expression_analysis

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.domain.entity.User
import com.joohnq.mood.ui.viewmodel.StatsIntent
import com.joohnq.mood.ui.viewmodel.StatsViewModel
import com.joohnq.onboarding.ui.event.OnboardingEvent
import com.joohnq.onboarding.ui.presentation.onboarding_expression_analysis.state.OnboardingExpressionAnalysisState
import com.joohnq.onboarding.ui.viewmodel.OnboardingViewModel
import com.joohnq.onboarding.ui.viewmodel.OnboardingViewModelIntent
import com.joohnq.shared.ui.CustomScreen
import com.joohnq.shared.ui.sharedViewModel
import com.joohnq.shared.ui.state.UiState
import com.joohnq.shared.ui.state.UiState.Companion.fold
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.joohnq.sleep_quality.ui.SleepQualityResource.Companion.toDomain
import com.joohnq.sleep_quality.ui.viewmodel.SleepQualityIntent
import com.joohnq.sleep_quality.ui.viewmodel.SleepQualityViewModel
import com.joohnq.stress_level.domain.entity.StressLevelRecord
import com.joohnq.stress_level.ui.StressLevelResource.Companion.toDomain
import com.joohnq.stress_level.ui.viewmodel.StressLevelIntent
import com.joohnq.stress_level.ui.viewmodel.StressLevelViewModel
import com.joohnq.user.ui.MedicationsSupplementsResource.Companion.toDomain
import com.joohnq.user.ui.PhysicalSymptomsResource.Companion.toDomain
import com.joohnq.user.ui.ProfessionalHelpResource.Companion.toDomain
import com.joohnq.user.ui.viewmodel.user.UserViewModel
import com.joohnq.user.ui.viewmodel.user.UserViewModelIntent
import com.joohnq.user.ui.viewmodel.user_preferences.UserPreferenceViewModel
import com.joohnq.user.ui.viewmodel.user_preferences.UserPreferenceViewModelIntent
import kotlinx.coroutines.launch

class OnboardingExpressionAnalysisScreen(
    private val onNavigateToGetUserName: () -> Unit,
    private val onGoBack: () -> Unit,
) : CustomScreen<OnboardingExpressionAnalysisState>() {
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
        val statsState by statsViewModel.state.collectAsState()
        val sleepQualityState by sleepQualityViewModel.state.collectAsState()
        val stressLevelState by stressLevelViewModel.state.collectAsState()
        val userPreferencesState by userPreferencesViewModel.state.collectAsState()

        fun onError(message: String) {
            scope.launch {
                snackBarState.showSnackbar(message)
            }
        }

        fun addSleepQualityRecord() {
            sleepQualityViewModel.onAction(
                SleepQualityIntent.AddSleepQualityRecord(
                    SleepQualityRecord(
                        sleepQuality = onboardingState.sleepQuality.toDomain()
                    )
                )
            )
        }

        fun addStressLevelRecord() {
            stressLevelViewModel.onAction(
                StressLevelIntent.AddStressLevelRecord(
                    StressLevelRecord(
                        stressLevel = onboardingState.stressLevel.toDomain()
                    )
                )
            )
        }

        fun addStatsRecord() {
            statsViewModel.onAction(StatsIntent.AddStatsRecord(onboardingState.statsRecord))
        }

        fun updateUser() {
            userViewModel.onAction(
                UserViewModelIntent.UpdateUser(
                    User(
                        physicalSymptoms = onboardingState.physicalSymptoms!!.toDomain(),
                        medicationsSupplements = onboardingState.medicationsSupplements!!.toDomain(),
                        soughtHelp = onboardingState.soughtHelp!!.toDomain()
                    )
                )
            )
        }

        fun resetStates() {
            sleepQualityViewModel.onAction(SleepQualityIntent.ResetAddingStatus)
            stressLevelViewModel.onAction(StressLevelIntent.ResetAddingStatus)
            statsViewModel.onAction(StatsIntent.ResetAddingStatus)
            userViewModel.onAction(UserViewModelIntent.ResetUpdatingStatus)
            onboardingViewModel.onAction(OnboardingViewModelIntent.ResetStatsRecord)
        }

        fun onEvent(event: OnboardingEvent) =
            when (event) {
                OnboardingEvent.OnNavigateToNext -> {
                    addSleepQualityRecord()
                    addStressLevelRecord()
                    addStatsRecord()
                    updateUser()
                }

                OnboardingEvent.OnGoBack -> onGoBack()
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
                },
                onAnyHasError = ::onError
            )
        }

        LaunchedEffect(userPreferencesState.updating) {
            userPreferencesState.updating.fold(
                onSuccess = {
                    resetStates()
                    onNavigateToGetUserName()
                },
                onError = ::onError
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


