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
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.joohnq.moodapp.constants.TestConstants
import com.joohnq.moodapp.entities.SleepQualityRecord
import com.joohnq.moodapp.entities.User
import com.joohnq.moodapp.entities.ValueSetValue
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.view.NextAndBackAction
import com.joohnq.moodapp.view.components.ExpressionAnalysisTextField
import com.joohnq.moodapp.view.routes.onNavigateToGetUserNameScreen
import com.joohnq.moodapp.view.state.UiState
import com.joohnq.moodapp.view.ui.Colors
import com.joohnq.moodapp.view.ui.TextStyles
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
    desc: ValueSetValue<String>,
    onAction: (NextAndBackAction) -> Unit = {},
) {
    OnboardingBaseComponent(
        page = 7,
        snackBarState = snackBarState,
        title = Res.string.expression_analysis_title,
        isContinueButtonVisible = desc.value.isNotEmpty(),
        onAction = onAction
    ) {
        Text(
            text = stringResource(Res.string.expression_analysis_desc),
            style = TextStyles.ParagraphMd(),
            color = Colors.Brown100Alpha64,
            textAlign = TextAlign.Center
        )
        ExpressionAnalysisTextField(
            modifier = Modifier.testTag(TestConstants.TEXT_INPUT),
            text = desc.value,
            onValueChange = desc.setValue
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
    val userState by userViewModel.userState.collectAsState()
    val statsState by statsViewModel.statsState.collectAsState()
    val sleepQualityState by sleepQualityViewModel.sleepQualityState.collectAsState()
    val stressLevelState by stressLevelViewModel.stressLevelState.collectAsState()

    LaunchedEffect(
        stressLevelState.adding.status,
        sleepQualityState.adding.status,
        statsState.adding.status,
        userState.updating.status
    ) {
        UiState.fold(
            stressLevelState.adding.status,
            sleepQualityState.adding.status,
            statsState.adding.status,
            userState.updating.status,
            onAllSuccess = {
                userPreferencesViewModel.onAction(UserPreferenceIntent.UpdateSkipOnboardingScreen())
                sleepQualityViewModel.resetAddingStatus()
                stressLevelViewModel.resetAddingStressLevel()
                statsViewModel.resetAddingStatsRecord()
                userViewModel.resetUpdatingStatus()
                onboardingViewModel.resetStatsRecord()
                navigation.onNavigateToGetUserNameScreen()
            },
            onAnyHasError = {
                scope.launch { snackBarState.showSnackbar(it) }
            }
        )
    }

    OnboardingExpressionAnalysisScreenUI(
        desc = ValueSetValue(
            onboardingState.statsRecord.description,
            onboardingViewModel::updateStatsRecordDescription
        ),
        snackBarState = snackBarState,
        onAction = { action ->
            when (action) {
                NextAndBackAction.OnContinue -> {
                    sleepQualityViewModel.addSleepQualityRecord(
                        SleepQualityRecord.Builder()
                            .setSleepQuality(sleepQuality = onboardingState.sleepQuality)
                            .build()
                    )
                    stressLevelViewModel.addStressLevelRecord(onboardingState.stressLevel)
                    statsViewModel.addStatsRecord(onboardingState.statsRecord)
                    userViewModel.updateUser(
                        User.init().copy(
                            physicalSymptoms = onboardingState.physicalSymptoms!!,
                            medicationsSupplements = onboardingState.medicationsSupplements!!,
                            soughtHelp = onboardingState.soughtHelp!!
                        )
                    )
                }

                NextAndBackAction.OnGoBack -> navigation.popBackStack()
            }
        }
    )
}

@Preview
@Composable
fun OnboardingExpressionAnalysisScreenUIPreview() {
    OnboardingExpressionAnalysisScreenUI(
        desc = ValueSetValue(""),
    )
}
