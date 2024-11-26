package com.joohnq.moodapp.ui.presentation.onboarding.onboarding_professional_help

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.domain.ProfessionalHelp
import com.joohnq.moodapp.ui.components.TextRadioButton
import com.joohnq.moodapp.ui.presentation.onboarding.OnboardingBaseComponent
import com.joohnq.moodapp.ui.presentation.onboarding.onboarding_professional_help.event.OnboardingProfessionalHelpEvent
import com.joohnq.moodapp.ui.presentation.onboarding.onboarding_professional_help.state.OnboardingProfessionalHelpState
import com.joohnq.moodapp.ui.theme.ComponentColors
import com.joohnq.moodapp.ui.theme.Dimens
import com.joohnq.moodapp.ui.theme.Drawables
import com.joohnq.moodapp.viewmodel.OnboardingIntent
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.sought_professional_help_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun OnboardingProfessionalHelpUI(
    state: OnboardingProfessionalHelpState,
) {
    val options = rememberSaveable { ProfessionalHelp.getAll() }

    OnboardingBaseComponent(
        page = 2,
        image = Drawables.Images.OnboardingSoughtProfessionalHelp,
        title = Res.string.sought_professional_help_title,
        isContinueButtonVisible = state.selectedOption != null,
        onGoBack = { state.onEvent(OnboardingProfessionalHelpEvent.OnGoBack) },
        onContinue = { state.onEvent(OnboardingProfessionalHelpEvent.OnNavigateToOnboardingPhysicalSymptomsScreen) }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            options.forEach { option ->
                TextRadioButton(
                    modifier = Modifier.weight(1f),
                    text = stringResource(option.text),
                    selected = state.selectedOption == option,
                    shape = Dimens.Shape.Circle,
                    colors = ComponentColors.RadioButton.TextRadioButtonColors(),
                    onClick = { state.onAction(OnboardingIntent.UpdateUserSoughtHelp(option)) }
                )
            }
        }
    }
}