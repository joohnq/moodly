package com.joohnq.onboarding.ui.presentation.onboarding_expression_analysis

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.core.ui.mapper.fold
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.domain.entity.User
import com.joohnq.mood.ui.viewmodel.StatSideEffect
import com.joohnq.mood.ui.viewmodel.StatsIntent
import com.joohnq.mood.ui.viewmodel.StatsViewModel
import com.joohnq.onboarding.ui.event.OnboardingEvent
import com.joohnq.onboarding.ui.viewmodel.OnboardingIntent
import com.joohnq.onboarding.ui.viewmodel.OnboardingViewModel
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.joohnq.sleep_quality.ui.mapper.toDomain
import com.joohnq.sleep_quality.ui.viewmodel.SleepQualityIntent
import com.joohnq.sleep_quality.ui.viewmodel.SleepQualitySideEffect
import com.joohnq.sleep_quality.ui.viewmodel.SleepQualityViewModel
import com.joohnq.stress_level.domain.entity.StressLevelRecord
import com.joohnq.stress_level.ui.mapper.toDomain
import com.joohnq.stress_level.ui.viewmodel.StressLevelIntent
import com.joohnq.stress_level.ui.viewmodel.StressLevelSideEffect
import com.joohnq.stress_level.ui.viewmodel.StressLevelViewModel
import com.joohnq.user.ui.mapper.toDomain
import com.joohnq.user.ui.viewmodel.user.UserIntent
import com.joohnq.user.ui.viewmodel.user.UserViewModel
import com.joohnq.user.ui.viewmodel.user_preferences.UserPreferenceIntent
import com.joohnq.user.ui.viewmodel.user_preferences.UserPreferencesViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

@Composable
fun OnboardingExpressionAnalysisScreen(
    onNavigateToUserName: () -> Unit,
    onGoBack: () -> Unit,
) {
    val onboardingViewModel: OnboardingViewModel = sharedViewModel()
    val userViewModel: UserViewModel = sharedViewModel()
    val statsViewModel: StatsViewModel = sharedViewModel()
    val sleepQualityViewModel: SleepQualityViewModel = sharedViewModel()
    val stressLevelViewModel: StressLevelViewModel = sharedViewModel()
    val userPreferencesViewModel: UserPreferencesViewModel = sharedViewModel()
    val scope = rememberCoroutineScope()
    val snackBarState = rememberSnackBarState()
    val onboardingState by onboardingViewModel.state.collectAsState()
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
                    sleepQuality = onboardingState.sleepQuality.toDomain(),
                    startSleeping = onboardingState.startSleepTime,
                    endSleeping = onboardingState.endSleepTime,
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
            UserIntent.UpdateUser(
                User(
                    physicalSymptoms = onboardingState.physicalSymptoms!!.toDomain(),
                    medicationsSupplements = onboardingState.medicationsSupplements!!.toDomain(),
                    soughtHelp = onboardingState.soughtHelp!!.toDomain()
                )
            )
        )
    }

    fun resetStates() {
        userViewModel.onAction(UserIntent.ResetUpdatingStatus)
        onboardingViewModel.onAction(OnboardingIntent.ResetStatsRecord)
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
        stressLevelViewModel,
        sleepQualityViewModel,
        statsViewModel
    ) {
        combine(
            stressLevelViewModel.sideEffect,
            sleepQualityViewModel.sideEffect,
            statsViewModel.sideEffect
        ) { stressSideEffect, sleepSideEffect, statsSideEffect ->
            Triple(stressSideEffect, sleepSideEffect, statsSideEffect)
        }.collect { (stressSideEffect, sleepSideEffect, statsSideEffect) ->
            if (stressSideEffect is StressLevelSideEffect.StressLevelAdded && sleepSideEffect is SleepQualitySideEffect.SleepQualityAdded && statsSideEffect is StatSideEffect.StatsAdded) {
                userPreferencesViewModel.onAction(UserPreferenceIntent.UpdateSkipOnboarding())
            }
        }
    }

    LaunchedEffect(userPreferencesState.updating) {
        userPreferencesState.updating.fold(
            onSuccess = {
                resetStates()
                onNavigateToUserName()
            },
            onError = ::onError
        )
    }

    OnboardingExpressionAnalysisUI(
        description = onboardingState.statsRecord.description,
        snackBarState = snackBarState,
        onAction = onboardingViewModel::onAction,
        onEvent = ::onEvent
    )
}
