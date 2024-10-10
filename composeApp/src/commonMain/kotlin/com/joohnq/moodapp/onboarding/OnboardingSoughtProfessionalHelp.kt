package com.joohnq.moodapp.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
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
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.joohnq.moodapp.Colors
import com.joohnq.moodapp.Drawables
import com.joohnq.moodapp.components.ButtonWithArrowRight
import com.joohnq.moodapp.components.CustomTextStyle
import com.joohnq.moodapp.components.OnboardingCustomRadioButton
import org.jetbrains.compose.resources.painterResource

sealed class OnboardingSoughtProfessionalHelpOptions(val text: String) {
    data object Yes : OnboardingSoughtProfessionalHelpOptions("Yes")
    data object No : OnboardingSoughtProfessionalHelpOptions("No")
    data object Indeterminate : OnboardingSoughtProfessionalHelpOptions("Indeterminate")
}

class OnboardingSoughtProfessionalHelp : Screen {
    @Composable
    override fun Content() {
        var isContinueButtonVisible by remember { mutableStateOf(false) }
        var selectedOption by remember {
            mutableStateOf<OnboardingSoughtProfessionalHelpOptions>(
                OnboardingSoughtProfessionalHelpOptions.Indeterminate
            )
        }
        val options = remember {
            listOf(
                OnboardingSoughtProfessionalHelpOptions.Yes,
                OnboardingSoughtProfessionalHelpOptions.No
            )
        }

        LaunchedEffect(selectedOption) {
            isContinueButtonVisible =
                selectedOption != OnboardingSoughtProfessionalHelpOptions.Indeterminate
        }
        Scaffold(
            containerColor = Colors.Brown10,
            modifier = Modifier.fillMaxSize()
        ) { padding ->
            Column(modifier = Modifier.padding(padding).padding(horizontal = 16.dp).fillMaxSize()) {
                OnboardingTopBar(2)
                Spacer(modifier = Modifier.height(32.dp))
                Image(
                    painter = painterResource(Drawables.Images.OnboardingSoughtProfessionalHelp),
                    contentDescription = null,
                    modifier = Modifier.widthIn(max = 300.dp).fillMaxWidth().aspectRatio(1f)
                        .align(alignment = Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(48.dp))
                Text(
                    "Have you sought \n professional help before?",
                    style = CustomTextStyle.TextStyleOnboardingScreenTitle()
                )
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    options.forEach { option ->
                        OnboardingCustomRadioButton(
                            modifier = Modifier.weight(1f),
                            text = option.text,
                            selected = selectedOption == option,
                            selectedBackground = Colors.Green50,
                            selectedContent = Colors.White,
                            unSelectedContent = Colors.Brown80,
                            unSelectedBackground = Colors.White,
                            onClick = { selectedOption = option }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                if (isContinueButtonVisible)
                    ButtonWithArrowRight(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Continue",
                        onClick = {})
            }
        }
    }
}