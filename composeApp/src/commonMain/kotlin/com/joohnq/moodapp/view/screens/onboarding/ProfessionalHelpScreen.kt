package com.joohnq.moodapp.view.screens.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joohnq.moodapp.entities.ProfessionalHelp
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.view.components.ProfessionalHelpRadioButton
import com.joohnq.moodapp.view.constants.Drawables
import com.joohnq.moodapp.view.routes.onNavigateToPhysicalSymptoms
import com.joohnq.moodapp.viewmodel.OnboardingViewModel
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.sought_professional_help_title

@Composable
fun ProfessionalHelpScreenUI(
    snackBarState: SnackbarHostState = remember { SnackbarHostState() },
    isContinueButtonVisible: Boolean,
    selectedOption: ProfessionalHelp?,
    setSelectedOption: (ProfessionalHelp) -> Unit,
    onGoBack: () -> Unit = {},
    onAction: () -> Unit,
) {
    val options = remember { ProfessionalHelp.getAll() }

    OnboardingBaseComponent(
        page = 2,
        snackBarState = snackBarState,
        image = Drawables.Images.OnboardingSoughtProfessionalHelp,
        title = Res.string.sought_professional_help_title,
        isContinueButtonVisible = isContinueButtonVisible,
        onGoBack = onGoBack,
        onContinue = onAction,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            options.forEach { option ->
                ProfessionalHelpRadioButton(
                    modifier = Modifier.weight(1f),
                    option = option,
                    selected = selectedOption == option,
                    onClick = { setSelectedOption(option) }
                )
            }
        }
    }
}

@Composable
fun ProfessionalHelpScreen(
    onboardingViewModel: OnboardingViewModel = sharedViewModel(),
    navigation: NavController,
) {
    var isContinueButtonVisible by remember { mutableStateOf(false) }
    var selectedOption by rememberSaveable(stateSaver = ProfessionalHelp.getSaver()) {
        mutableStateOf(null)
    }
    val snackBarState = remember { SnackbarHostState() }

    LaunchedEffect(selectedOption) {
        isContinueButtonVisible = selectedOption != null
    }

    ProfessionalHelpScreenUI(
        snackBarState = snackBarState,
        isContinueButtonVisible = isContinueButtonVisible,
        selectedOption = selectedOption,
        setSelectedOption = { selectedOption = it },
        onGoBack = navigation::popBackStack,
        onAction = {
            onboardingViewModel.updateUserSoughtHelp(selectedOption?.value!!)
            navigation.onNavigateToPhysicalSymptoms()
        }
    )
}
