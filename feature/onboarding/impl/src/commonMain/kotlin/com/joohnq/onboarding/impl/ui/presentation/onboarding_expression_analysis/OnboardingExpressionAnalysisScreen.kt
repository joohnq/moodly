package com.joohnq.onboarding.impl.ui.presentation.onboarding_expression_analysis

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.api.entity.Quad
import com.joohnq.api.entity.User
import com.joohnq.mood.impl.ui.mapper.MoodRecordResourceMapper.toDomain
import com.joohnq.mood.impl.ui.presentation.overview.MoodOverviewContract
import com.joohnq.mood.impl.ui.presentation.overview.MoodOverviewViewModel
import com.joohnq.onboarding.impl.ui.event.OnboardingEvent
import com.joohnq.onboarding.impl.ui.viewmodel.OnboardingViewModel
import com.joohnq.preferences.impl.ui.viewmodel.PreferencesContract
import com.joohnq.preferences.impl.ui.viewmodel.PreferencesViewModel
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.sleep_quality.impl.ui.mapper.SleepQualityResourceMapper.toDomain
import com.joohnq.sleep_quality.impl.ui.presentation.overview.SleepQualityOverviewContract
import com.joohnq.sleep_quality.impl.ui.presentation.overview.SleepQualityOverviewViewModel
import com.joohnq.stress_level.impl.ui.mapper.StressLevelRecordResourceMapper.toDomain
import com.joohnq.stress_level.impl.ui.presentation.overview.StressLevelOverviewContract
import com.joohnq.stress_level.impl.ui.presentation.overview.StressLevelOverviewViewModel
import com.joohnq.ui.ObserverSideEffects
import com.joohnq.ui.sharedViewModel
import com.joohnq.user.impl.ui.mapper.MedicationsSupplementsResourceMapper.toDomain
import com.joohnq.user.impl.ui.mapper.PhysicalSymptomsResourceMapper.toDomain
import com.joohnq.user.impl.ui.mapper.ProfessionalHelpResourceMapper.toDomain
import com.joohnq.user.impl.ui.viewmodel.UserContract
import com.joohnq.user.impl.ui.viewmodel.UserViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

@Composable
fun OnboardingExpressionAnalysisScreen(
    onNavigateToUserName: () -> Unit,
    onGoBack: () -> Unit,
    onboardingViewModel: OnboardingViewModel = sharedViewModel(),
    userViewModel: UserViewModel = sharedViewModel(),
    moodOverviewViewModel: MoodOverviewViewModel = sharedViewModel(),
    sleepQualityOverviewViewModel: SleepQualityOverviewViewModel = sharedViewModel(),
    stressLevelOverviewViewModel: StressLevelOverviewViewModel = sharedViewModel(),
    preferencesViewModel: PreferencesViewModel = sharedViewModel(),
) {
    val scope = rememberCoroutineScope()
    val snackBarState = rememberSnackBarState()
    val onboardingState by onboardingViewModel.state.collectAsState()

    fun onError(message: String) {
        scope.launch {
            snackBarState.showSnackbar(message)
        }
    }

    fun addSleepQualityRecord() {
        sleepQualityOverviewViewModel.onIntent(
            SleepQualityOverviewContract.Intent.Add(
                onboardingState.sleepQuality.toDomain()
            )
        )
    }

    fun addStressLevelRecord() {
        stressLevelOverviewViewModel.onIntent(
            StressLevelOverviewContract.Intent.Add(
                onboardingState.stressLevel.toDomain()
            )
        )
    }

    fun addMoodRecord() {
        moodOverviewViewModel.onIntent(
            MoodOverviewContract.Intent.Add(
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
        stressLevelOverviewViewModel.sideEffect,
        sleepQualityOverviewViewModel.sideEffect,
        moodOverviewViewModel.sideEffect,
        userViewModel.sideEffect
    ) {
        combine(
            stressLevelOverviewViewModel.sideEffect,
            sleepQualityOverviewViewModel.sideEffect,
            moodOverviewViewModel.sideEffect,
            userViewModel.sideEffect
        ) { stressSideEffect, sleepSideEffect, statsSideEffect, userSideEffect ->
            Quad(stressSideEffect, sleepSideEffect, statsSideEffect, userSideEffect)
        }.collect { (stressSideEffect, sleepSideEffect, statsSideEffect, userSideEffect) ->
            if (stressSideEffect is StressLevelOverviewContract.SideEffect.ShowError) {
                onError(stressSideEffect.error)
            }
            if (sleepSideEffect is SleepQualityOverviewContract.SideEffect.ShowError) {
                onError(sleepSideEffect.error)
            }
            if (statsSideEffect is MoodOverviewContract.SideEffect.ShowError) {
                onError(statsSideEffect.error)
            }
            if (userSideEffect is UserContract.SideEffect.ShowError) {
                onError(userSideEffect.error)
            }

            if (
                stressSideEffect is StressLevelOverviewContract.SideEffect.Added &&
                sleepSideEffect is SleepQualityOverviewContract.SideEffect.Added &&
                statsSideEffect is MoodOverviewContract.SideEffect.Added &&
                userSideEffect is UserContract.SideEffect.Updated
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
