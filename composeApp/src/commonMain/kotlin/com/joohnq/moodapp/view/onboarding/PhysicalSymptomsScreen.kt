package com.joohnq.moodapp.view.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.joohnq.moodapp.view.components.PhysicalSymptomsRadioButton
import com.joohnq.moodapp.view.components.TextStyles
import com.joohnq.moodapp.view.entities.PhysicalSymptoms
import com.joohnq.moodapp.view.entities.PhysicalSymptomsSaver
import com.joohnq.moodapp.viewmodel.UserViewModel
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.experiencing_physical_symptoms_title
import moodapp.composeapp.generated.resources.select_one_answer
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

class PhysicalSymptomsScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        var isContinueButtonVisible by remember { mutableStateOf(false) }
        val userViewModel: UserViewModel = koinInject()
        var selectedOption by rememberSaveable(stateSaver = PhysicalSymptomsSaver) {
            mutableStateOf(null)
        }
        val options = remember { PhysicalSymptoms.getAll() }

        LaunchedEffect(selectedOption) {
            isContinueButtonVisible = selectedOption != null
        }

        OnboardingBaseComponent(
            page = 3,
            title = Res.string.experiencing_physical_symptoms_title,
            isContinueButtonVisible = isContinueButtonVisible,
            onBack = { navigator.pop() },
            onContinue = {
                userViewModel.setUserPhysicalPain(selectedOption ?: PhysicalSymptoms.No)
                navigator.push(SleepQualityScreen())
            },
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
                options.forEach { option: PhysicalSymptoms ->
                    PhysicalSymptomsRadioButton(
                        option = option,
                        selected = selectedOption == option,
                    ) { selectedOption = option }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}