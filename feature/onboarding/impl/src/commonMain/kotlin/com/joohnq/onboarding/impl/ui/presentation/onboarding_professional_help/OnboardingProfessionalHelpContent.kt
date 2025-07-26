package com.joohnq.onboarding.impl.ui.presentation.onboarding_professional_help

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.onboarding.impl.ui.event.OnboardingEvent
import com.joohnq.onboarding.impl.ui.viewmodel.OnboardingContract
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.radio_button.TextRadioButton
import com.joohnq.shared_resources.sought_professional_help_title
import com.joohnq.shared_resources.theme.ComponentColors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.user.impl.ui.mapper.getAllProfessionalHelpResource
import com.joohnq.user.impl.ui.resource.ProfessionalHelpResource

@Composable
fun OnboardingProfessionalHelpContent(
    state: ProfessionalHelpResource?,
    onEvent: (OnboardingEvent) -> Unit = {},
    onAction: (OnboardingContract.Intent) -> Unit = {},
) {
    val options = rememberSaveable { getAllProfessionalHelpResource() }

    _root_ide_package_.com.joohnq.onboarding.impl.ui.presentation.OnboardingBaseComponent(
        page = 2,
        image = Drawables.Images.OnboardingSoughtProfessionalHelp,
        title = Res.string.sought_professional_help_title,
        isContinueButtonVisible = state != null,
        onGoBack = { onEvent(OnboardingEvent.OnGoBack) },
        onContinue = { onEvent(OnboardingEvent.OnNavigateToNext) }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            options.forEach { option: ProfessionalHelpResource ->
                TextRadioButton(
                    modifier = Modifier.weight(1f),
                    text = option.text,
                    selected = state == option,
                    shape = Dimens.Shape.Circle,
                    colors = ComponentColors.RadioButton.textRadioButtonColors(),
                    onClick = { onAction(OnboardingContract.Intent.UpdateUserSoughtHelp(option)) }
                )
            }
        }
    }
}