package com.joohnq.moodapp.ui.presentation.onboarding.onboarding_stress_level

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.domain.StressLevel
import com.joohnq.moodapp.ui.components.TextRadioButton
import com.joohnq.moodapp.ui.components.VerticalSpacer
import com.joohnq.moodapp.ui.presentation.onboarding.OnboardingBaseComponent
import com.joohnq.moodapp.ui.presentation.onboarding.onboarding_stress_level.event.OnboardingStressLevelEvent
import com.joohnq.moodapp.ui.presentation.onboarding.onboarding_stress_level.state.OnboardingStressLevelState
import com.joohnq.moodapp.ui.theme.Colors
import com.joohnq.moodapp.ui.theme.ComponentColors
import com.joohnq.moodapp.ui.theme.Dimens
import com.joohnq.moodapp.ui.theme.TextStyles
import com.joohnq.moodapp.viewmodel.OnboardingIntent
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.stress_rate_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun OnboardingStressLevelUI(
    state: OnboardingStressLevelState,
) {
    val (selectedOption, onAction, onEvent) = state
    val options: List<StressLevel> = remember { StressLevel.getAll() }

    OnboardingBaseComponent(
        page = 6,
        title = Res.string.stress_rate_title,
        onGoBack = { onEvent(OnboardingStressLevelEvent.OnGoBack) },
        onContinue = { onEvent(OnboardingStressLevelEvent.OnNavigateToOnboardingExpressionAnalysisScreen) }
    ) {
        Text(
            text = stringResource(selectedOption.value),
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
                    modifier = Modifier.weight(1f).aspectRatio(1f),
                    text = stringResource(option.value),
                    selected = selectedOption == option,
                    shape = Dimens.Shape.Circle,
                    colors = ComponentColors.RadioButton.StressLevelRadioButtonColors(),
                    onClick = { onAction(OnboardingIntent.UpdateStressLevel(option)) }
                )
            }
        }
        VerticalSpacer(16.dp)
        Text(
            text = stringResource(selectedOption.text),
            style = TextStyles.TextLgBold(),
            color = Colors.Brown80
        )
    }
}