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
import com.joohnq.stress_level.impl.ui.presentation.stress_level.StressLevelContract
import com.joohnq.stress_level.impl.ui.presentation.stress_level.StressLevelViewModel
import com.joohnq.ui.ObserverSideEffects
import com.joohnq.ui.sharedViewModel
import com.joohnq.user.impl.ui.mapper.toDomain
import com.joohnq.user.impl.ui.viewmodel.UserContract
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
        sleepQualityViewModel.onIntent(
            SleepQualityContract.Intent.Add(
                onboardingState.sleepQuality.toDomain(),
            )
        )
    }

    fun addStressLevelRecord() {
        stressLevelViewModel.onIntent(
            StressLevelContract.Intent.Add(
                onboardingState.stressLevel.toDomain()
            )
        )
    }

    fun addMoodRecord() {
        moodViewModel.onIntent(
            MoodContract.Intent.Add(
                onboardingState.moodRecord.toDomain()
            )
        )
    }

    fun updateUser() {
        userViewModel.onIntent(
            UserContract.Intent.Update(
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
            if (stressSideEffect is StressLevelContract.SideEffect.ShowError) {
                onError(stressSideEffect.error)
            }
            if (sleepSideEffect is SleepQualityContract.SideEffect.ShowError) {
                onError(sleepSideEffect.error)
            }
            if (statsSideEffect is MoodContract.SideEffect.ShowError) {
                onError(statsSideEffect.error)
            }
            if (userSideEffect is UserContract.SideEffect.ShowError) {
                onError(userSideEffect.error)
            }

            if (
                stressSideEffect is StressLevelContract.SideEffect.StressLevelAdded &&
                sleepSideEffect is SleepQualityContract.SideEffect.SleepQualityAdded &&
                statsSideEffect is MoodContract.SideEffect.StatsAdded &&
                userSideEffect is UserContract.SideEffect.UpdatedUser
            ) {
                preferencesViewModel.onIntent(PreferencesContract.Intent.UpdateSkipOnboarding())
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
        onAction = onboardingViewModel::onIntent,
        onEvent = ::onEvent
    )
}