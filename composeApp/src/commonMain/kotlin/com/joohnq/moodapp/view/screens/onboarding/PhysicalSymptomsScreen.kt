package com.joohnq.moodapp.view.screens.onboarding

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joohnq.moodapp.entities.PhysicalSymptoms
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.view.components.PhysicalSymptomsRadioButton
import com.joohnq.moodapp.view.components.TextStyles
import com.joohnq.moodapp.view.routes.onNavigateToSleepQuality
import com.joohnq.moodapp.viewmodel.OnboardingViewModel
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.experiencing_physical_symptoms_title
import moodapp.composeapp.generated.resources.select_one_answer
import org.jetbrains.compose.resources.stringResource

@Composable
fun PhysicalSymptomsScreenUI(
    snackBarState: SnackbarHostState = remember { SnackbarHostState() },
    selectedOption: PhysicalSymptoms?,
    setSelectedOption: (PhysicalSymptoms) -> Unit = {},
    onGoBack: () -> Unit = {},
    onAction: () -> Unit = {},
) {
    val options: List<PhysicalSymptoms> = remember { PhysicalSymptoms.getAll() }

    OnboardingBaseComponent(
        page = 3,
        snackBarState = snackBarState,
        title = Res.string.experiencing_physical_symptoms_title,
        isContinueButtonVisible = selectedOption != null,
        onGoBack = onGoBack,
        onContinue = onAction,
    ) {
        Text(
            text = stringResource(Res.string.select_one_answer),
            style = TextStyles.OnboardingScreenMood(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(40.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            options.forEach { option: PhysicalSymptoms ->
                PhysicalSymptomsRadioButton(
                    option = option,
                    selected = selectedOption == option,
                    onClick = { setSelectedOption(option) }
                )
            }
        }
    }
}


@Composable
fun PhysicalSymptomsScreen(
    onboardingViewModel: OnboardingViewModel = sharedViewModel(),
    navigation: NavController,
) {
    val onboardingState by onboardingViewModel.onboardingState.collectAsState()
    val snackBarState = remember { SnackbarHostState() }

    PhysicalSymptomsScreenUI(
        snackBarState = snackBarState,
        selectedOption = onboardingState.physicalSymptoms,
        setSelectedOption = onboardingViewModel::updateUserPhysicalSymptoms,
        onGoBack = navigation::popBackStack,
        onAction = navigation::onNavigateToSleepQuality
    )
}


@Preview
@Composable
fun PhysicalSymptomsScreenPreview() {
    PhysicalSymptomsScreenUI(
        selectedOption = PhysicalSymptoms.No,
    )
}