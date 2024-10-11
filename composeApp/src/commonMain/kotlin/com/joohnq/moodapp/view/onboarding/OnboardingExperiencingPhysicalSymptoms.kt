package com.joohnq.moodapp.view.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.joohnq.moodapp.Colors
import com.joohnq.moodapp.view.components.ButtonWithArrowRight
import com.joohnq.moodapp.view.components.CustomTextStyle
import com.joohnq.moodapp.view.components.IconAndTextRadioButton
import com.joohnq.moodapp.view.onboarding.state.OnboardingExperiencingPhysicalSymptomsOptions
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.continue_word
import moodapp.composeapp.generated.resources.experiencing_physical_symptoms_title
import moodapp.composeapp.generated.resources.select_one_answer
import org.jetbrains.compose.resources.stringResource

class OnboardingExperiencingPhysicalSymptoms : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        var isContinueButtonVisible by remember { mutableStateOf(false) }
        var selectedOption by remember {
            mutableStateOf<OnboardingExperiencingPhysicalSymptomsOptions>(
                OnboardingExperiencingPhysicalSymptomsOptions.Indeterminate
            )
        }
        val options = remember {
            listOf(
                OnboardingExperiencingPhysicalSymptomsOptions.YesVeryPainful,
                OnboardingExperiencingPhysicalSymptomsOptions.No,
                OnboardingExperiencingPhysicalSymptomsOptions.YesJustABit
            )
        }

        LaunchedEffect(selectedOption) {
            isContinueButtonVisible =
                selectedOption != OnboardingExperiencingPhysicalSymptomsOptions.Indeterminate
        }
        Scaffold(
            containerColor = Colors.Brown10,
            modifier = Modifier.fillMaxSize()
        ) { padding ->
            Column(
                modifier = Modifier.padding(padding).padding(horizontal = 16.dp).fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OnboardingTopBar(3)
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    text = stringResource(Res.string.experiencing_physical_symptoms_title),
                    style = CustomTextStyle.TextStyleOnboardingScreenTitle()
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = stringResource(Res.string.select_one_answer),
                    style = CustomTextStyle.TextStyleOnboardingScreenMood(),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(40.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    options.forEach { option ->
                        IconAndTextRadioButton(
                            modifier = Modifier,
                            paddingValues = PaddingValues(vertical = 20.dp, horizontal = 16.dp),
                            text = stringResource(option.text),
                            icon = option.icon.copy(modifier = Modifier.size(24.dp)),
                            selected = selectedOption == option,
                            selectedBackground = Colors.Green50,
                            selectedContent = Colors.White,
                            unSelectedContent = Colors.Brown80,
                            unSelectedBackground = Colors.White,
                            shape = RoundedCornerShape(20.dp),
                            onClick = { selectedOption = option }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                if (isContinueButtonVisible)
                    ButtonWithArrowRight(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(Res.string.continue_word),
                        onClick = { navigator.push(OnboardingSleepQuality()) })
            }
        }
    }
}