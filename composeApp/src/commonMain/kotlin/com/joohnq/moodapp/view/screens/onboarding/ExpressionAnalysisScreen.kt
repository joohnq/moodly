package com.joohnq.moodapp.view.screens.onboarding

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.joohnq.moodapp.constants.TestConstants
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.view.components.ExpressionAnalysisTextField
import com.joohnq.moodapp.view.components.TextStyles
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
fun ExpressionAnalysisScreenUI(
    navigation: NavController = rememberNavController(),
    snackBarState: SnackbarHostState = remember { SnackbarHostState() },
    desc: String,
    setDesc: (String) -> Unit = {},
    onAction: () -> Unit = {},
) {
    OnboardingBaseComponent(
        page = 7,
        snackBarState = snackBarState,
        title = Res.string.expression_analysis_title,
        onGoBack = navigation::popBackStack,
        isContinueButtonVisible = desc.isNotEmpty(),
        onContinue = onAction,
    ) {
        Text(
            stringResource(Res.string.expression_analysis_desc),
            style = TextStyles.ExpressionAnalysisDesc()
        )
        ExpressionAnalysisTextField(
            modifier = Modifier.testTag(TestConstants.TEXT_INPUT),
            desc,
            onValueChange = setDesc
        )
    }
}

@Composable
fun ExpressionAnalysisScreen(
    onboardingViewModel: OnboardingViewModel = sharedViewModel(),
    userPreferencesViewModel: UserPreferenceViewModel = sharedViewModel(),
    sleepQualityViewModel: SleepQualityViewModel = sharedViewModel(),
    stressLevelViewModel: StressLevelViewModel = sharedViewModel(),
    statsViewModel: StatsViewModel = sharedViewModel(),
    userViewModel: UserViewModel = sharedViewModel(),
    navigation: NavController
) {
    val scope = rememberCoroutineScope()
    var desc by remember { mutableStateOf("") }
    val snackBarState = remember { SnackbarHostState() }
    val onboardingState by onboardingViewModel.onboardingState.collectAsState()
    val sleepQualityState by sleepQualityViewModel.sleepQualityState.collectAsState()
    val stressLevelState by stressLevelViewModel.stressLevelState.collectAsState()
    val statsState by statsViewModel.statsState.collectAsState()
    val userState by userViewModel.userState.collectAsState()

    LaunchedEffect(
        stressLevelState.addingStatus,
        sleepQualityState.addingStatus,
        statsState.addingStatus,
        userState.addingStatus
    ) {
        scope.launch {
            stressLevelState.addingStatus.showErrorOrUnit(snackBarState)
            sleepQualityState.addingStatus.showErrorOrUnit(snackBarState)
            statsState.addingStatus.showErrorOrUnit(snackBarState)
            userState.addingStatus.showErrorOrUnit(snackBarState)
        }

        if (
            stressLevelState.addingStatus is UiState.Success &&
            sleepQualityState.addingStatus is UiState.Success &&
            statsState.addingStatus is UiState.Success &&
            userState.addingStatus is UiState.Success
        ) {
            userPreferencesViewModel.onAction(UserPreferenceIntent.UpdateSkipOnboardingScreen(value = true))
            navigation.onNavigateToGetUserNameScreen()
        }
    }

    ExpressionAnalysisScreenUI(
        desc = desc,
        setDesc = { desc = it },
        snackBarState = snackBarState,
        onAction = {
            onboardingViewModel.updateStatsRecordDescription(desc)
            sleepQualityViewModel.addSleepQualityRecord(onboardingState.sleepQuality)
            stressLevelViewModel.addStressLevelRecord(onboardingState.stressLevel)
            statsViewModel.addStatsRecord(onboardingState.statsRecord)
            userViewModel.updateUser(onboardingState.user)
        },
    )
}

@Preview
@Composable
fun ExpressionAnalysisScreenUIPreview() {
    ExpressionAnalysisScreenUI(
        desc = "",
        setDesc = {},
    )
}
