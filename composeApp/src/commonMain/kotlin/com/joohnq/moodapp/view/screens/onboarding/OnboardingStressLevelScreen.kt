package com.joohnq.moodapp.view.screens.onboarding

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joohnq.moodapp.entities.StressLevel
import com.joohnq.moodapp.entities.ValueSetValue
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.view.NextAndBackAction
import com.joohnq.moodapp.view.components.TextRadioButton
import com.joohnq.moodapp.view.components.VerticalSpacer
import com.joohnq.moodapp.view.routes.onNavigateToOnboardingExpressionAnalysis
import com.joohnq.moodapp.view.ui.Colors
import com.joohnq.moodapp.view.ui.ComponentColors
import com.joohnq.moodapp.view.ui.Dimens
import com.joohnq.moodapp.view.ui.TextStyles
import com.joohnq.moodapp.viewmodel.OnboardingViewModel
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.stress_rate_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun OnboardingStressLevelScreenUI(
    selectedOption: ValueSetValue<StressLevel>,
    onAction: (NextAndBackAction) -> Unit = {},
) {
    val options: List<StressLevel> = remember { StressLevel.getAll() }

    OnboardingBaseComponent(
        page = 6,
        title = Res.string.stress_rate_title,
        onAction = onAction
    ) {
        Text(
            text = stringResource(selectedOption.value.value),
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
                    selected = selectedOption.value == option,
                    shape = Dimens.Shape.Circle,
                    colors = ComponentColors.RadioButton.StressLevelRadioButtonColors(),
                    onClick = { selectedOption.setValue(option) }
                )
            }
        }
        VerticalSpacer(16.dp)
        Text(
            text = stringResource(selectedOption.value.text),
            style = TextStyles.TextLgBold(),
            color = Colors.Brown80
        )
    }
}

@Composable
fun OnboardingStressLevelScreen(
    navigation: NavController,
    onboardingViewModel: OnboardingViewModel = sharedViewModel(),
) {
    val onboardingState by onboardingViewModel.onboardingState.collectAsState()

    OnboardingStressLevelScreenUI(
        selectedOption = ValueSetValue(
            onboardingState.stressLevel,
            onboardingViewModel::updateStressLevel
        ),
        onAction = { action ->
            when (action) {
                NextAndBackAction.OnContinue -> navigation.onNavigateToOnboardingExpressionAnalysis()
                NextAndBackAction.OnGoBack -> navigation.popBackStack()
            }
        }
    )
}

@Preview
@Composable
fun OnboardingStressLevelScreenPreview() {
    OnboardingStressLevelScreenUI(
        selectedOption = ValueSetValue(StressLevel.One),
    )
}
