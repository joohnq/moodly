package com.joohnq.moodapp.onboarding

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
import com.joohnq.moodapp.Drawables
import com.joohnq.moodapp.components.ButtonWithArrowRight
import com.joohnq.moodapp.components.CustomTextStyle
import com.joohnq.moodapp.components.IconAndTextRadioButton
import com.joohnq.moodapp.entities.IconProps

sealed class OnboardingExperiencingPhysicalSymptomsOptions(val text: String, val icon: IconProps) {
    data object YesVeryPainful : OnboardingExperiencingPhysicalSymptomsOptions(
        text = "Yes, Very Painful.", icon = IconProps(
            icon = Drawables.Icons.Check,
        )
    )

    data object No : OnboardingExperiencingPhysicalSymptomsOptions(
        text = "No Physical Pain At All.",
        icon = IconProps(icon = Drawables.Icons.Close)
    )

    data object YesJustABit : OnboardingExperiencingPhysicalSymptomsOptions(
        text = "Yes, But just a bit",
        icon = IconProps(icon = Drawables.Icons.Question)
    )

    data object Indeterminate :
        OnboardingExperiencingPhysicalSymptomsOptions(
            text = "Indeterminate",
            icon = IconProps(icon = Drawables.Icons.Question)
        )
}

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
                    "Are you experiencing any physical symptoms of distress?",
                    style = CustomTextStyle.TextStyleOnboardingScreenTitle()
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    "Select one answer",
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
                            text = option.text,
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
                        text = "Continue",
                        onClick = { navigator.push(OnboardingSleepQuality()) })
            }
        }
    }
}