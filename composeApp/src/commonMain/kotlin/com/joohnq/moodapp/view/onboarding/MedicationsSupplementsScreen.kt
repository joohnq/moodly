package com.joohnq.moodapp.view.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.joohnq.moodapp.CustomColors
import com.joohnq.moodapp.view.components.ButtonWithArrowRight
import com.joohnq.moodapp.view.components.CustomTextStyle
import com.joohnq.moodapp.view.components.IconAndTextRadioButtonColors
import com.joohnq.moodapp.view.components.IconAndTextRadioButtonVertical
import com.joohnq.moodapp.view.components.OnboardingTopBar
import com.joohnq.moodapp.view.onboarding.state.MedicationsSupplementsOptions
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.continue_word
import moodapp.composeapp.generated.resources.medications_supplements_title
import org.jetbrains.compose.resources.stringResource

class MedicationsSupplementsScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        var isContinueButtonVisible by remember { mutableStateOf(false) }
        var selectedOption by remember {
            mutableStateOf<MedicationsSupplementsOptions>(
                MedicationsSupplementsOptions.Indeterminate
            )
        }
        val options = remember {
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

        Scaffold(modifier = Modifier.fillMaxSize(), containerColor = CustomColors.Brown10) { padding ->
            Column(
                modifier = Modifier.padding(padding).fillMaxSize().padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OnboardingTopBar(1)
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    stringResource(Res.string.medications_supplements_title),
                    style = CustomTextStyle.TextStyleOnboardingScreenTitle()
                )
                Spacer(modifier = Modifier.height(48.dp))
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxWidth(),
                    userScrollEnabled = false,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(options) { option ->
                        IconAndTextRadioButtonVertical(
                            modifier = Modifier.fillMaxSize().aspectRatio(1f),
                            paddingValues = PaddingValues(all = 16.dp),
                            text = stringResource(option.text),
                            icon = option.icon.copy(modifier = Modifier.size(24.dp)),
                            selected = selectedOption == option,
                            iconAndTextRadioButtonColors = IconAndTextRadioButtonColors(
                                selectedBackgroundColor = CustomColors.Green50,
                                selectedContentColor = CustomColors.White,
                                unSelectedContentColor = CustomColors.Brown80,
                                unSelectedBackgroundColor = CustomColors.White,
                                selectedBorderColor = CustomColors.Green50Alpha25,
                            ),
                            shape = RoundedCornerShape(20.dp),
                            textStyle = CustomTextStyle.TextStyleOnboardingMedicationsGridItem(),
                            onClick = { selectedOption = option }
                        )
                    }
                }
                if (isContinueButtonVisible)
                    ButtonWithArrowRight(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(Res.string.continue_word),
                        onClick = { navigator.push(SleepQualityScreen()) })
            }
        }
    }
}