package com.joohnq.moodapp.view.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.joohnq.moodapp.Drawables
import com.joohnq.moodapp.view.components.ProfessionalHelpRadioButton
import com.joohnq.moodapp.view.entities.ProfessionalHelpOptions
import com.joohnq.moodapp.view.entities.ProfessionalHelpOptionsSaver
import com.joohnq.moodapp.viewmodel.UserViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.sought_professional_help_title
import org.koin.compose.koinInject

class ProfessionalHelpScreen : Screen {
    @Composable
    override fun Content() {
        val scope = rememberCoroutineScope()
        val ioDispatcher: CoroutineDispatcher = koinInject()
        val navigator = LocalNavigator.currentOrThrow
        var isContinueButtonVisible by remember { mutableStateOf(false) }
        val userViewModel: UserViewModel = koinInject()
        var selectedOption by rememberSaveable(stateSaver = ProfessionalHelpOptionsSaver) {
            mutableStateOf(null)
        }
        val options = rememberSaveable {
            listOf(
                ProfessionalHelpOptions.Yes,
                ProfessionalHelpOptions.No
            )
        }

        LaunchedEffect(selectedOption) {
            isContinueButtonVisible = selectedOption != null
        }

        OnboardingBaseComponent(
            page = 2,
            image = Drawables.Images.OnboardingSoughtProfessionalHelp,
            title = Res.string.sought_professional_help_title,
            isContinueButtonVisible = isContinueButtonVisible,
            onBack = { navigator.pop() },
            onContinue = { onSomethingWentWrong ->
                scope.launch(ioDispatcher) {
                    val res = userViewModel.setUserSoughtHelp(selectedOption?.value ?: false)
                    if (!res) {
                        onSomethingWentWrong()
                        return@launch
                    }

                    navigator.push(PhysicalSymptomsScreen())
                }
            },
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                options.forEach { option ->
                    ProfessionalHelpRadioButton(
                        modifier = Modifier.weight(1f),
                        option = option,
                        selected = selectedOption == option,
                    ) { selectedOption = option }
                }
            }
        }
    }
}