package com.joohnq.moodapp.view.screens.onboarding

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.joohnq.moodapp.view.routes.onNavigateToExpressionAnalysis
import com.joohnq.moodapp.viewmodel.OnboardingViewModel
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.stress_rate_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun StressLevelScreenUI(
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
            stringResource(selectedOption.value),
            style = TextStyles.StressRate()
        )
        Spacer(modifier = Modifier.height(16.dp))
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
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            stringResource(selectedOption.text),
            style = TextStyles.StressRateDesc()
        )
    }
}

@Composable
fun StressLevelScreen(
    navigation: NavController,
    onboardingViewModel: OnboardingViewModel = sharedViewModel(),
) {
    val onboardingState by onboardingViewModel.onboardingState.collectAsState()
    val snackBarState = remember { SnackbarHostState() }

    StressLevelScreenUI(
        snackBarState = snackBarState,
        selectedOption = onboardingState.stressLevel,
        setSelectedOption = onboardingViewModel::updateStressLevel,
        onGoBack = navigation::popBackStack,
        onAction = navigation::onNavigateToExpressionAnalysis
    )
}

@Preview
@Composable
fun StressLevelScreenPreview() {
    StressLevelScreenUI(
        selectedOption = StressLevel.One,
    )
}
