package com.joohnq.moodapp.view.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.joohnq.moodapp.view.components.MedicationsSupplementsRadioButton
import com.joohnq.moodapp.view.onboarding.options.MedicationsSupplementsOptions
import com.joohnq.moodapp.view.onboarding.options.MedicationsSupplementsOptionsSaver
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.medications_supplements_title

class MedicationsSupplementsScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        var isContinueButtonVisible by remember { mutableStateOf(false) }
        var selectedOption by rememberSaveable(stateSaver = MedicationsSupplementsOptionsSaver) {
            mutableStateOf(
                MedicationsSupplementsOptions.Indeterminate
            )
        }
        val options = rememberSaveable {
            listOf(
                MedicationsSupplementsOptions.OverTheCounterSupplements,
                MedicationsSupplementsOptions.PrescribedMedications,
                MedicationsSupplementsOptions.ImNotTakingAny,
                MedicationsSupplementsOptions.PreferNotToSay
            )
        }

        LaunchedEffect(selectedOption) {
            isContinueButtonVisible =
                selectedOption != MedicationsSupplementsOptions.Indeterminate
        }

        OnboardingBaseComponent(
            page = 5,
            title = Res.string.medications_supplements_title,
            isContinueButtonVisible = isContinueButtonVisible,
            onBack = { navigator.pop() },
            onContinue = { navigator.push(StressRateScreen()) },
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize().aspectRatio(1f),
                userScrollEnabled = false,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(options) { option ->
                    MedicationsSupplementsRadioButton(
                        option = option,
                        selected = selectedOption == option,
                    ) { selectedOption = option }
                }
            }
        }
    }
}