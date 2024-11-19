package com.joohnq.moodapp.view.screens.onboarding

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joohnq.moodapp.entities.ProfessionalHelp
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.view.NextAndBackAction
import com.joohnq.moodapp.view.components.TextRadioButton
import com.joohnq.moodapp.view.routes.onNavigateToPhysicalSymptoms
import com.joohnq.moodapp.view.ui.ComponentColors
import com.joohnq.moodapp.view.ui.Dimens
import com.joohnq.moodapp.view.ui.Drawables
import com.joohnq.moodapp.viewmodel.OnboardingIntent
import com.joohnq.moodapp.viewmodel.OnboardingViewModel
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.sought_professional_help_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun OnboardingProfessionalHelpScreenUI(
    selectedOption: ProfessionalHelp?,
    onNavigation: (NextAndBackAction) -> Unit = {},
    onAction: (OnboardingIntent) -> Unit = {},
) {
    val options = rememberSaveable { ProfessionalHelp.getAll() }

    OnboardingBaseComponent(
        page = 2,
        image = Drawables.Images.OnboardingSoughtProfessionalHelp,
        title = Res.string.sought_professional_help_title,
        isContinueButtonVisible = selectedOption != null,
        onAction = onNavigation
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            options.forEach { option ->
                TextRadioButton(
                    modifier = Modifier.weight(1f),
                    text = stringResource(option.text),
                    selected = selectedOption == option,
                    shape = Dimens.Shape.Circle,
                    colors = ComponentColors.RadioButton.TextRadioButtonColors(),
                    onClick = { onAction(OnboardingIntent.UpdateUserSoughtHelp(option)) }
                )
            }
        }
    }
}

@Composable
fun OnboardingProfessionalHelpScreen(
    onboardingViewModel: OnboardingViewModel = sharedViewModel(),
    navigation: NavController,
) {
    val onboardingState by onboardingViewModel.onboardingState.collectAsState()

    OnboardingProfessionalHelpScreenUI(
        selectedOption = onboardingState.soughtHelp,
        onAction = onboardingViewModel::onAction,
        onNavigation = { action ->
            when (action) {
                NextAndBackAction.OnContinue -> navigation.onNavigateToPhysicalSymptoms()
                NextAndBackAction.OnGoBack -> navigation.popBackStack()
            }
        }
    )
}

@Preview
@Composable
fun OnboardingProfessionalHelpScreenPreview() {
    OnboardingProfessionalHelpScreenUI(
        selectedOption = ProfessionalHelp.No,
    )
}