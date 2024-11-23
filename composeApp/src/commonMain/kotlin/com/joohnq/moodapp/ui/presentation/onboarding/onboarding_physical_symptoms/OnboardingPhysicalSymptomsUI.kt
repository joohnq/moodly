package com.joohnq.moodapp.ui.presentation.onboarding.onboarding_physical_symptoms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.domain.PhysicalSymptoms
import com.joohnq.moodapp.ui.components.IconAndTextRadioButtonHorizontal
import com.joohnq.moodapp.ui.components.VerticalSpacer
import com.joohnq.moodapp.ui.presentation.onboarding.OnboardingBaseComponent
import com.joohnq.moodapp.ui.presentation.onboarding.onboarding_physical_symptoms.state.OnboardingPhysicalSymptomsState
import com.joohnq.moodapp.ui.presentation.onboarding.onboarding_professional_help.event.OnboardingProfessionalHelpEvent
import com.joohnq.moodapp.ui.theme.Colors
import com.joohnq.moodapp.ui.theme.ComponentColors
import com.joohnq.moodapp.ui.theme.Dimens
import com.joohnq.moodapp.ui.theme.TextStyles
import com.joohnq.moodapp.viewmodel.OnboardingIntent
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.experiencing_physical_symptoms_title
import moodapp.composeapp.generated.resources.select_one_answer
import org.jetbrains.compose.resources.stringResource

@Composable
fun OnboardingPhysicalSymptomsUI(
    state: OnboardingPhysicalSymptomsState
) {
    val (selectedOption, onEvent, onAction) = state
    val options = remember { PhysicalSymptoms.getAll() }

    OnboardingBaseComponent(
        page = 3,
        title = Res.string.experiencing_physical_symptoms_title,
        isContinueButtonVisible = selectedOption != null,
        onGoBack = { onEvent(OnboardingProfessionalHelpEvent.OnGoBack) },
        onContinue = { onEvent(OnboardingProfessionalHelpEvent.OnNavigateToOnboardingPhysicalSymptomsScreen) }
    ) {
        Text(
            text = stringResource(Res.string.select_one_answer),
            style = TextStyles.ParagraphMd(),
            textAlign = TextAlign.Center,
            color = Colors.Brown100Alpha64
        )
        VerticalSpacer(40.dp)
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            options.forEach { option: PhysicalSymptoms ->
                IconAndTextRadioButtonHorizontal(
                    modifier = Modifier.fillMaxWidth(),
                    paddingValues = PaddingValues(all = 16.dp),
                    text = stringResource(option.text),
                    icon = option.icon.copy(modifier = Modifier.size(Dimens.Icon)),
                    selected = selectedOption == option,
                    colors = ComponentColors.RadioButton.TextRadioButtonColors(),
                    shape = Dimens.Shape.Medium,
                    textStyle = TextStyles.TextLgExtraBold(),
                    onClick = { onAction(OnboardingIntent.UpdateUserPhysicalSymptoms(option)) }
                )
            }
        }
    }
}