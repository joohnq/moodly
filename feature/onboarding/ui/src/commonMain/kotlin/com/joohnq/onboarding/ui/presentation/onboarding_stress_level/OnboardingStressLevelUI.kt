package com.joohnq.onboarding.ui.presentation.onboarding_stress_level

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
import com.joohnq.mood.components.TextRadioButton
import com.joohnq.mood.components.VerticalSpacer
import com.joohnq.mood.theme.Colors
import com.joohnq.mood.theme.ComponentColors
import com.joohnq.mood.theme.Dimens
import com.joohnq.mood.theme.TextStyles
import com.joohnq.onboarding.ui.presentation.OnboardingBaseComponent
import com.joohnq.onboarding.ui.presentation.onboarding_stress_level.event.OnboardingStressLevelEvent
import com.joohnq.onboarding.ui.presentation.onboarding_stress_level.state.OnboardingStressLevelState
import com.joohnq.onboarding.ui.viewmodel.OnboardingViewModelIntent
import com.joohnq.shared.ui.Res
import com.joohnq.shared.ui.stress_rate_title
import com.joohnq.stress_level.domain.entity.StressLevel
import org.jetbrains.compose.resources.stringResource

@Composable
fun OnboardingStressLevelUI(
    state: OnboardingStressLevelState,
) {
    val options: List<StressLevel> = remember { StressLevel.getAll() }

    OnboardingBaseComponent(
        page = 6,
        title = Res.string.stress_rate_title,
        onGoBack = { state.onEvent(OnboardingStressLevelEvent.OnGoBack) },
        onContinue = { state.onEvent(OnboardingStressLevelEvent.OnNavigateToOnboardingExpressionAnalysisScreen) }
    ) {
        Text(
            text = stringResource(state.selectedOption.value),
            style = TextStyles.DisplayLgExtraBold(),
            color = Colors.Brown80
        )
        VerticalSpacer(16.dp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            options.forEach { option: StressLevel ->
                TextRadioButton(
                    modifier = Modifier.weight(1f).aspectRatio(1f).testTag(option.id.toString()),
                    text = stringResource(option.value),
                    selected = state.selectedOption == option,
                    shape = Dimens.Shape.Circle,
                    colors = ComponentColors.RadioButton.StressLevelRadioButtonColors(),
                    onClick = { state.onAction(OnboardingViewModelIntent.UpdateStressLevel(option)) }
                )
            }
        }
        VerticalSpacer(16.dp)
        Text(
            text = stringResource(state.selectedOption.text),
            style = TextStyles.TextLgBold(),
            color = Colors.Brown80
        )
    }
}