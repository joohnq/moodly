package com.joohnq.moodapp.view.onboarding

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.joohnq.moodapp.Colors
import com.joohnq.moodapp.view.components.TextStyles
import com.joohnq.moodapp.view.onboarding.options.StressRateOptions
import com.joohnq.moodapp.view.onboarding.options.StressRateOptionsSaver
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.stress_rate_title
import org.jetbrains.compose.resources.stringResource

class StressRateScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        var selectedOption by rememberSaveable(stateSaver = StressRateOptionsSaver) {
            mutableStateOf(
                StressRateOptions.Three
            )
        }
        val options = remember {
            listOf(
                StressRateOptions.One,
                StressRateOptions.Two,
                StressRateOptions.Three,
                StressRateOptions.Four,
                StressRateOptions.Five
            )
        }

        OnboardingBaseComponent(
            page = 6,
            title = Res.string.stress_rate_title,
            onBack = { navigator.pop() },
            onContinue = { navigator.push(ExpressionAnalysisScreen()) },
        ) {
            Text(
                stringResource(selectedOption.value),
                style = TextStyles.StressRate()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                options.forEach { option ->
                    val isSelected = selectedOption == option
                    Button(
                        modifier = Modifier.weight(1f).aspectRatio(1f),
                        onClick = { selectedOption = option },
                        colors = ButtonColors(
                            containerColor = if (isSelected) Colors.Orange40 else Colors.White,
                            contentColor = if (isSelected) Colors.White else Colors.Brown80,
                            disabledContainerColor = if (isSelected) Colors.Orange40 else Colors.White,
                            disabledContentColor = if (isSelected) Colors.White else Colors.Brown80
                        ),
                        border = if (isSelected) BorderStroke(
                            color = Colors.Orange40Alpha25,
                            width = 4.dp
                        ) else null,
                        shape = CircleShape,
                        contentPadding = PaddingValues(0.dp),
                    ) {
                        Text(text = stringResource(option.value))
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                stringResource(selectedOption.text),
                style = TextStyles.StressRateDesc()
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}