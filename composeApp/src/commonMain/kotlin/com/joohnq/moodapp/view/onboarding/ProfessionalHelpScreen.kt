package com.joohnq.moodapp.view.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.joohnq.moodapp.Colors
import com.joohnq.moodapp.Drawables
import com.joohnq.moodapp.view.components.TextRadioButton
import com.joohnq.moodapp.view.onboarding.options.ProfessionalHelpOptions
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.sought_professional_help_title
import org.jetbrains.compose.resources.stringResource

class ProfessionalHelpScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        var isContinueButtonVisible by remember { mutableStateOf(false) }
        var selectedOption by remember {
            mutableStateOf<ProfessionalHelpOptions>(
                ProfessionalHelpOptions.Indeterminate
            )
        }
        val options = remember {
            listOf(
                ProfessionalHelpOptions.Yes,
                ProfessionalHelpOptions.No
            )
        }

        LaunchedEffect(selectedOption) {
            isContinueButtonVisible =
                selectedOption != ProfessionalHelpOptions.Indeterminate
        }

        OnboardingBaseComponent(
            page = 2,
            image = Drawables.Images.OnboardingSoughtProfessionalHelp,
            title = Res.string.sought_professional_help_title,
            isContinueButtonVisible = isContinueButtonVisible,
            onContinue = { navigator.push(PhysicalSymptomsScreen()) },
        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                options.forEach { option ->
                    TextRadioButton(
                        modifier = Modifier.weight(1f),
                        text = stringResource(option.text),
                        selected = selectedOption == option,
                        selectedBackground = Colors.Green50,
                        selectedContent = Colors.White,
                        unSelectedContent = Colors.Brown80,
                        unSelectedBackground = Colors.White,
                        shape = CircleShape,
                        onClick = { selectedOption = option }
                    )
                }
            }
        }
    }
}