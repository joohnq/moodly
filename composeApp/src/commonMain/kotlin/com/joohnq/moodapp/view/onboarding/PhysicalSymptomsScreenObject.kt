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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.model.entities.PhysicalSymptoms
import com.joohnq.moodapp.view.components.PhysicalSymptomsRadioButton
import com.joohnq.moodapp.view.components.TextStyles
import com.joohnq.moodapp.viewmodel.UserViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.experiencing_physical_symptoms_title
import moodapp.composeapp.generated.resources.select_one_answer
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Serializable
object PhysicalSymptomsScreenObject

@Composable
fun PhysicalSymptomsScreen(
    onGoBack: () -> Unit,
    onNavigateToSleepQuality: () -> Unit
) {
    var isContinueButtonVisible by remember { mutableStateOf(false) }
    val userViewModel: UserViewModel = koinViewModel()
    var selectedOption by rememberSaveable(stateSaver = PhysicalSymptoms.getSaver()) {
        mutableStateOf(null)
    }
    val options: List<PhysicalSymptoms> = remember { PhysicalSymptoms.getAll() }
    val scope = rememberCoroutineScope()
    val ioDispatcher: CoroutineDispatcher = koinInject()

    LaunchedEffect(selectedOption) {
        isContinueButtonVisible = selectedOption != null
    }

    OnboardingBaseComponent(
        page = 3,
        title = Res.string.experiencing_physical_symptoms_title,
        isContinueButtonVisible = isContinueButtonVisible,
        onBack = onGoBack,
        onContinue = { onSomethingWentWrong ->
            scope.launch(ioDispatcher) {
                val res =
                    userViewModel.setUserPhysicalPain(selectedOption ?: PhysicalSymptoms.No)
                if (!res) {
                    onSomethingWentWrong()
                    return@launch
                }

                onNavigateToSleepQuality()
            }
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
