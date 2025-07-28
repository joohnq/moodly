package com.joohnq.onboarding.impl.ui.presentation.onboarding_stress_level

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.joohnq.onboarding.impl.ui.event.OnboardingEvent
import com.joohnq.onboarding.impl.ui.viewmodel.OnboardingContract
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.radio_button.TextRadioButton
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.stress_rate_title
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.ComponentColors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.stress_level.impl.ui.mapper.StressLevelResourceMapper.allStressLevelResource
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource
import com.joohnq.stress_level.impl.ui.resource.StressLevelResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun OnboardingStressLevelContent(
    state: StressLevelRecordResource,
    onEvent: (OnboardingEvent) -> Unit = {},
    onAction: (OnboardingContract.Intent) -> Unit = {},
) {
    val options: List<StressLevelResource> = remember { allStressLevelResource() }

    _root_ide_package_.com.joohnq.onboarding.impl.ui.presentation.OnboardingBaseComponent(
        page = 6,
        title = Res.string.stress_rate_title,
        onGoBack = { onEvent(OnboardingEvent.OnGoBack) },
        onContinue = { onEvent(OnboardingEvent.OnNavigateToNext) }
    ) {
        Text(
            text = stringResource(state.stressLevel.value),
            style = TextStyles.displayLgExtraBold(),
            color = Colors.Brown80
        )
        VerticalSpacer(16.dp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            options.forEach { option: StressLevelResource ->
                TextRadioButton(
                    modifier = Modifier.weight(1f).aspectRatio(1f).testTag(option.id.toString()),
                    text = option.value,
                    selected = state.stressLevel == option,
                    shape = Dimens.Shape.Circle,
                    colors = ComponentColors.RadioButton.stressLevelRadioButtonColors(),
                    onClick = { onAction(OnboardingContract.Intent.UpdateStressLevel(option)) }
                )
            }
        }
        VerticalSpacer(16.dp)
        Text(
            text = stringResource(state.stressLevel.text),
            style = TextStyles.textLgBold(),
            color = Colors.Brown80
        )
    }
}
