package com.joohnq.onboarding.impl.presentation.onboarding_expression_analysis

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.api.entity.User
import com.joohnq.mood.impl.ui.mapper.toDomain
import com.joohnq.mood.impl.ui.presentation.mood.MoodContract
import com.joohnq.mood.impl.ui.presentation.mood.MoodViewModel
import com.joohnq.onboarding.impl.event.OnboardingEvent
import com.joohnq.onboarding.impl.viewmodel.OnboardingViewModel
import com.joohnq.preferences.impl.ui.viewmodel.PreferencesContract
import com.joohnq.preferences.impl.ui.viewmodel.PreferencesViewModel
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.sleep_quality.impl.ui.mapper.toDomain
import com.joohnq.sleep_quality.impl.ui.presentation.sleep_quality.SleepQualityContract
import com.joohnq.sleep_quality.impl.ui.presentation.sleep_quality.SleepQualityViewModel
import com.joohnq.stress_level.impl.ui.mapper.toDomain
import com.joohnq.stress_level.impl.ui.viewmodel.StressLevelIntent
import com.joohnq.stress_level.impl.ui.viewmodel.StressLevelSideEffect
import com.joohnq.stress_level.impl.ui.viewmodel.StressLevelViewModel
import com.joohnq.ui.ObserverSideEffects
import com.joohnq.ui.sharedViewModel
import com.joohnq.user.impl.ui.mapper.toDomain
import com.joohnq.user.impl.ui.viewmodel.UserIntent
import com.joohnq.user.impl.ui.viewmodel.UserSideEffect
import com.joohnq.user.impl.ui.viewmodel.UserViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

data class Quad<out A, out B, out C, out D>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D,
)

@Composable
fun OnboardingExpressionAnalysisScreen(
    onNavigateToUserName: () -> Unit,
    onGoBack: () -> Unit,
) {
    val onboardingViewModel: OnboardingViewModel = sharedViewModel()
    val userViewModel: UserViewModel = sharedViewModel()
    val moodViewModel: MoodViewModel = sharedViewModel()
    val sleepQualityViewModel: SleepQualityViewModel = sharedViewModel()
    val stressLevelViewModel: StressLevelViewModel = sharedViewModel()
    val preferencesViewModel: PreferencesViewModel = sharedViewModel()
    val scope = rememberCoroutineScope()
    val snackBarState = rememberSnackBarState()
    val onboardingState by onboardingViewModel.state.collectAsState()

    fun onError(message: String) {
        scope.launch {
            snackBarState.showSnackbar(message)
        }
    }

    fun addSleepQualityRecord() {
        sleepQualityViewModel.onAction(
            SleepQualityContract.Intent.Add(
                onboardingState.sleepQuality.toDomain(),
            )
        )
    }

    fun addStressLevelRecord() {
        stressLevelViewModel.onAction(
            StressLevelIntent.Add(
                onboardingState.stressLevel.toDomain()
            )
        )
    }

    fun addMoodRecord() {
        moodViewModel.onAction(
            MoodContract.Intent.Add(
                onboardingState.moodRecord.toDomain()
            )
        )
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

    fun onEvent(event: OnboardingEvent) =
        when (event) {
            OnboardingEvent.OnNavigateToNext -> {
                addSleepQualityRecord()
                addStressLevelRecord()
                addMoodRecord()
                updateUser()
            }

            OnboardingEvent.OnGoBack -> onGoBack()
        }

    LaunchedEffect(
        stressLevelViewModel.sideEffect,
        sleepQualityViewModel.sideEffect,
        moodViewModel.sideEffect,
        userViewModel.sideEffect
    ) {
        combine(
            stressLevelViewModel.sideEffect,
            sleepQualityViewModel.sideEffect,
            moodViewModel.sideEffect,
            userViewModel.sideEffect
        ) { stressSideEffect, sleepSideEffect, statsSideEffect, userSideEffect ->
            Quad(stressSideEffect, sleepSideEffect, statsSideEffect, userSideEffect)
        }.collect { (stressSideEffect, sleepSideEffect, statsSideEffect, userSideEffect) ->
            if (stressSideEffect is StressLevelSideEffect.ShowError) {
                onError(stressSideEffect.error)
            }
            if (sleepSideEffect is SleepQualityContract.SideEffect.ShowError) {
                onError(sleepSideEffect.error)
            }
            if (statsSideEffect is MoodContract.SideEffect.ShowError) {
                onError(statsSideEffect.error)
            }
            if (userSideEffect is UserSideEffect.ShowError) {
                onError(userSideEffect.error)
            }

            if (stressSideEffect is StressLevelSideEffect.StressLevelAdded && sleepSideEffect is SleepQualityContract.SideEffect.SleepQualityAdded && statsSideEffect is MoodContract.SideEffect.StatsAdded && userSideEffect is UserSideEffect.UpdatedUser) {
                preferencesViewModel.onAction(PreferencesContract.Intent.UpdateSkipOnboarding())
            }
        }
    }

    ObserverSideEffects(
        flow = preferencesViewModel.sideEffect,
        onEvent = { effect ->
            when (effect) {
                is PreferencesContract.SideEffect.ShowError -> onError(effect.message)
                PreferencesContract.SideEffect.UpdatedPreferences -> onNavigateToUserName()
            }
        }
    )

    OnboardingExpressionAnalysisContent(
        description = onboardingState.moodRecord.description,
        snackBarState = snackBarState,
        onAction = onboardingViewModel::onAction,
        onEvent = ::onEvent
    )
}