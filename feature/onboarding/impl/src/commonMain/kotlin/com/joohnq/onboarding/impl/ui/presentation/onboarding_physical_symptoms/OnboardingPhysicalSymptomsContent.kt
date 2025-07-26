package com.joohnq.onboarding.impl.ui.presentation.onboarding_physical_symptoms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.onboarding.impl.components.PhysicalSymptomsRadioButton
import com.joohnq.onboarding.impl.event.OnboardingEvent
import com.joohnq.onboarding.impl.presentation.OnboardingBaseComponent
import com.joohnq.onboarding.impl.ui.viewmodel.OnboardingContract
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.experiencing_physical_symptoms_title
import com.joohnq.shared_resources.select_one_answer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.user.impl.ui.mapper.getAllPhysicalSymptomsResource
import com.joohnq.user.impl.ui.resource.PhysicalSymptomsResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun OnboardingPhysicalSymptomsContent(
    state: PhysicalSymptomsResource?,
    onEvent: (com.joohnq.onboarding.impl.ui.event.OnboardingEvent) -> Unit = {},
    onAction: (OnboardingContract.Intent) -> Unit = {}
) {
    val options = remember { getAllPhysicalSymptomsResource() }

    _root_ide_package_.com.joohnq.onboarding.impl.ui.presentation.OnboardingBaseComponent(
        page = 3,
        title = Res.string.experiencing_physical_symptoms_title,
        isContinueButtonVisible = state != null,
        onGoBack = { onEvent(_root_ide_package_.com.joohnq.onboarding.impl.ui.event.OnboardingEvent.OnGoBack) },
        onContinue = { onEvent(_root_ide_package_.com.joohnq.onboarding.impl.ui.event.OnboardingEvent.OnNavigateToNext) }
    ) {
        Text(
            text = stringResource(Res.string.select_one_answer),
            style = TextStyles.paragraphMd(),
            textAlign = TextAlign.Center,
            color = Colors.Brown100Alpha64
        )
        VerticalSpacer(40.dp)
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            options.forEach { option: PhysicalSymptomsResource ->
                _root_ide_package_.com.joohnq.onboarding.impl.ui.components.PhysicalSymptomsRadioButton(
                    modifier = Modifier.fillMaxWidth().testTag(option.id.toString()),
                    text = stringResource(option.text),
                    icon = option.icon,
                    selected = state == option,
                    onClick = {
                        onAction(
                            OnboardingContract.Intent.UpdateUserPhysicalSymptoms(option)
                        )
                    }
                )
            }
        }
    }
}