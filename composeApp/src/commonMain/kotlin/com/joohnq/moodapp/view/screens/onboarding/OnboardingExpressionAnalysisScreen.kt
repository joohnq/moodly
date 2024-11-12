package com.joohnq.moodapp.view.screens.onboarding

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavController
import com.joohnq.moodapp.constants.TestConstants
import com.joohnq.moodapp.entities.User
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.view.components.ExpressionAnalysisTextField
import com.joohnq.moodapp.view.components.TextStyles
import com.joohnq.moodapp.view.constants.Colors
import com.joohnq.moodapp.view.routes.onNavigateToGetUserNameScreen
import com.joohnq.moodapp.view.state.UiState
import com.joohnq.moodapp.view.state.UiState.Companion.showErrorOrUnit
import com.joohnq.moodapp.viewmodel.OnboardingViewModel
import com.joohnq.moodapp.viewmodel.SleepQualityViewModel
import com.joohnq.moodapp.viewmodel.StatsViewModel
import com.joohnq.moodapp.viewmodel.StressLevelViewModel
import com.joohnq.moodapp.viewmodel.UserPreferenceIntent
import com.joohnq.moodapp.viewmodel.UserPreferenceViewModel
import com.joohnq.moodapp.viewmodel.UserViewModel
import kotlinx.coroutines.launch
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.expression_analysis_desc
import moodapp.composeapp.generated.resources.expression_analysis_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun OnboardingExpressionAnalysisScreenUI(
    snackBarState: SnackbarHostState = remember { SnackbarHostState() },
    desc: String,
    onGoBack: () -> Unit = {},
    setDesc: (String) -> Unit = {},
    onAction: () -> Unit = {},
) {
    OnboardingBaseComponent(
        page = 7,
        snackBarState = snackBarState,
        title = Res.string.expression_analysis_title,
        onGoBack = onGoBack,
        isContinueButtonVisible = desc.isNotEmpty(),
        onContinue = onAction,
    ) {
        Text(
            text = stringResource(Res.string.expression_analysis_desc),
            style = TextStyles.ParagraphMd(),
            color = Colors.Brown100Alpha64
        )
        ExpressionAnalysisTextField(
            modifier = Modifier.testTag(TestConstants.TEXT_INPUT),
            desc,
            onValueChange = setDesc
        )
    }
}

@Composable
fun OnboardingExpressionAnalysisScreen(
    onboardingViewModel: OnboardingViewModel = sharedViewModel(),
    userPreferencesViewModel: UserPreferenceViewModel = sharedViewModel(),
    sleepQualityViewModel: SleepQualityViewModel = sharedViewModel(),
    stressLevelViewModel: StressLevelViewModel = sharedViewModel(),
    statsViewModel: StatsViewModel = sharedViewModel(),
    userViewModel: UserViewModel = sharedViewModel(),
    navigation: NavController
) {
    val scope = rememberCoroutineScope()
    val snackBarState = remember { SnackbarHostState() }
    val onboardingState by onboardingViewModel.onboardingState.collectAsState()
    val userUpdatingStatus by userViewModel.updatingStatus.collectAsState()
    val statsAddingStatus by statsViewModel.addingStatus.collectAsState()
    val sleepQualityAddingStatus by sleepQualityViewModel.addingStatus.collectAsState()
    val stressLevelAddingStatus by stressLevelViewModel.addingStatus.collectAsState()

    LaunchedEffect(
        stressLevelAddingStatus,
        sleepQualityAddingStatus,
        statsAddingStatus,
        userUpdatingStatus
    ) {
        scope.launch {
            stressLevelAddingStatus.showErrorOrUnit(snackBarState)
            sleepQualityAddingStatus.showErrorOrUnit(snackBarState)
            statsAddingStatus.showErrorOrUnit(snackBarState)
            userUpdatingStatus.showErrorOrUnit(snackBarState)
        }

        if (
            stressLevelAddingStatus is UiState.Success &&
            sleepQualityAddingStatus is UiState.Success &&
            statsAddingStatus is UiState.Success &&
            userUpdatingStatus is UiState.Success
        ) {
            userPreferencesViewModel.onAction(UserPreferenceIntent.UpdateSkipOnboardingScreen())
            sleepQualityViewModel.resetAddingStatus()
            stressLevelViewModel.resetAddingStatus()
            statsViewModel.resetAddingStatus()
            userViewModel.resetUpdatingStatus()
            navigation.onNavigateToGetUserNameScreen()
        }
    }

    OnboardingExpressionAnalysisScreenUI(
        desc = onboardingState.statsRecord.description,
        setDesc = { onboardingViewModel.updateStatsRecordDescription(it) },
        onGoBack = navigation::popBackStack,
        snackBarState = snackBarState,
        onAction = {
            sleepQualityViewModel.addSleepQualityRecord(onboardingState.sleepQuality)
            stressLevelViewModel.addStressLevelRecord(onboardingState.stressLevel)
            statsViewModel.addStatsRecord(onboardingState.statsRecord)
            userViewModel.updateUser(
                User.init().copy(
                    physicalSymptoms = onboardingState.physicalSymptoms!!,
                    medicationsSupplements = onboardingState.medicationsSupplements!!,
                    soughtHelp = onboardingState.soughtHelp!!
                )
            )
        },
    )
}

@Preview
@Composable
fun OnboardingExpressionAnalysisScreenUIPreview() {
    OnboardingExpressionAnalysisScreenUI(
        desc = "",
    )
}
