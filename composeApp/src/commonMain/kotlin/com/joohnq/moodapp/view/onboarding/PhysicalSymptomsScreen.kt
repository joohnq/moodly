package com.joohnq.moodapp.view.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.joohnq.moodapp.Colors
import com.joohnq.moodapp.view.components.IconAndTextRadioButtonColors
import com.joohnq.moodapp.view.components.IconAndTextRadioButtonHorizontal
import com.joohnq.moodapp.view.components.TextStyles
import com.joohnq.moodapp.view.onboarding.options.PhysicalSymptomsOptions
import com.joohnq.moodapp.view.onboarding.options.PhysicalSymptomsOptionsSaver
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.experiencing_physical_symptoms_title
import moodapp.composeapp.generated.resources.select_one_answer
import org.jetbrains.compose.resources.stringResource

class PhysicalSymptomsScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        var isContinueButtonVisible by remember { mutableStateOf(false) }
        var selectedOption by rememberSaveable(stateSaver = PhysicalSymptomsOptionsSaver) {
            mutableStateOf(
                PhysicalSymptomsOptions.Indeterminate
            )
        }
        val options = remember {
            listOf(
                PhysicalSymptomsOptions.YesVeryPainful,
                PhysicalSymptomsOptions.No,
                PhysicalSymptomsOptions.YesJustABit
            )
        }

        LaunchedEffect(selectedOption) {
            isContinueButtonVisible =
                selectedOption != PhysicalSymptomsOptions.Indeterminate
        }

        OnboardingBaseComponent(
            page = 3,
            title = Res.string.experiencing_physical_symptoms_title,
            isContinueButtonVisible = isContinueButtonVisible,
            onBack = { navigator.pop() },
            onContinue = { navigator.push(SleepQualityScreen()) },
        ) {
            Text(
                text = stringResource(Res.string.select_one_answer),
                style = TextStyles.OnboardingScreenMood(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(40.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                options.forEach { option ->
                    IconAndTextRadioButtonHorizontal(
                        modifier = Modifier.fillMaxWidth(),
                        paddingValues = PaddingValues(all = 16.dp),
                        text = stringResource(option.text),
                        icon = option.icon.copy(modifier = Modifier.size(24.dp)),
                        selected = selectedOption == option,
                        iconAndTextRadioButtonColors = IconAndTextRadioButtonColors(
                            selectedBackgroundColor = Colors.Green50,
                            selectedContentColor = Colors.White,
                            unSelectedContentColor = Colors.Brown80,
                            unSelectedBackgroundColor = Colors.White,
                            selectedBorderColor = Colors.Green50Alpha25,
                        ),
                        shape = RoundedCornerShape(20.dp),
                        textStyle = TextStyles.WelcomeScreenButton(),
                        onClick = { selectedOption = option }
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}