package com.joohnq.onboarding.ui.presentation.onboarding_physical_symptoms

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.onboarding.ui.components.PhysicalSymptomsRadioButton
import com.joohnq.onboarding.ui.event.OnboardingEvent
import com.joohnq.onboarding.ui.presentation.OnboardingBaseComponent
import com.joohnq.onboarding.ui.viewmodel.OnboardingIntent
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.experiencing_physical_symptoms_title
import com.joohnq.shared_resources.select_one_answer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.ComponentColors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.user.ui.mapper.getAllPhysicalSymptomsResource
import com.joohnq.user.ui.resource.PhysicalSymptomsResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun OnboardingPhysicalSymptomsUI(
    physicalSymptoms: PhysicalSymptomsResource?,
    onEvent: (OnboardingEvent) -> Unit = {},
    onAction: (OnboardingIntent) -> Unit = {},
) {
    val options = remember { getAllPhysicalSymptomsResource() }

    OnboardingBaseComponent(
        page = 3,
        title = Res.string.experiencing_physical_symptoms_title,
        isContinueButtonVisible = physicalSymptoms != null,
        onGoBack = { onEvent(OnboardingEvent.OnGoBack) },
        onContinue = { onEvent(OnboardingEvent.OnNavigateToNext) }
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
            options.forEach { option: PhysicalSymptomsResource ->
                PhysicalSymptomsRadioButton(
                    modifier = Modifier.fillMaxWidth().testTag(option.id.toString()),
                    paddingValues = PaddingValues(all = 16.dp),
                    text = stringResource(option.text),
                    icon = option.icon.copy(modifier = Modifier.size(Dimens.Icon)),
                    selected = physicalSymptoms == option,
                    colors = ComponentColors.RadioButton.TextRadioButtonColors(),
                    shape = Dimens.Shape.Medium,
                    textStyle = TextStyles.TextLgExtraBold(),
                    onClick = {
                        onAction(
                            OnboardingIntent.UpdateUserPhysicalSymptoms(option)
                        )
                    }
                )
            }
        }
    }
}