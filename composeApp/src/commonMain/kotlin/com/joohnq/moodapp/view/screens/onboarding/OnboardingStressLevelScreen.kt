package com.joohnq.moodapp.view.screens.onboarding

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joohnq.moodapp.entities.StressLevel
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.view.components.StressRateButton
import com.joohnq.moodapp.view.components.TextStyles
import com.joohnq.moodapp.view.components.VerticalSpacer
import com.joohnq.moodapp.view.constants.Colors
import com.joohnq.moodapp.view.routes.onNavigateToOnboardingExpressionAnalysis
import com.joohnq.moodapp.viewmodel.OnboardingViewModel
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.stress_rate_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun OnboardingStressLevelScreenUI(
    snackBarState: SnackbarHostState = remember { SnackbarHostState() },
    selectedOption: StressLevel,
    setSelectedOption: (StressLevel) -> Unit = {},
    onGoBack: () -> Unit = {},
    onAction: () -> Unit = {}
) {
    val options: List<StressLevel> = remember { StressLevel.getAll() }

    OnboardingBaseComponent(
        page = 6,
        snackBarState = snackBarState,
        title = Res.string.stress_rate_title,
        onGoBack = onGoBack,
        onContinue = onAction,
    ) {
        Text(
            text = stringResource(selectedOption.value),
            style = TextStyles.DisplayLgExtraBold(),
            color = Colors.Brown80
        )
        VerticalSpacer(16.dp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            options.forEach { option: StressLevel ->
                StressRateButton(
                    modifier = Modifier.weight(1f),
                    option = option,
                    isSelected = selectedOption == option,
                    onClick = { setSelectedOption(option) }
                )
            }
        }
        VerticalSpacer(16.dp)
        Text(
            text = stringResource(selectedOption.text),
            style = TextStyles.TextLgBold(),
            color = Colors.Brown80
        )
    }
}

@Composable
fun OnboardingStressLevelScreen(
    navigation: NavController,
    onboardingViewModel: OnboardingViewModel = sharedViewModel(),
) {
    val onboardingState by onboardingViewModel.onboardingState.collectAsState()
    val snackBarState = remember { SnackbarHostState() }

    OnboardingStressLevelScreenUI(
        snackBarState = snackBarState,
        selectedOption = onboardingState.stressLevel,
        setSelectedOption = onboardingViewModel::updateStressLevel,
        onGoBack = navigation::popBackStack,
        onAction = navigation::onNavigateToOnboardingExpressionAnalysis
    )
}

@Preview
@Composable
fun OnboardingStressLevelScreenPreview() {
    OnboardingStressLevelScreenUI(
        selectedOption = StressLevel.One,
    )
}
