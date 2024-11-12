package com.joohnq.moodapp.view.screens.onboarding

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joohnq.moodapp.constants.TestConstants
import com.joohnq.moodapp.entities.Mood
import com.joohnq.moodapp.entities.SleepQuality
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.view.components.DoubleText
import com.joohnq.moodapp.view.components.MoodFace
import com.joohnq.moodapp.view.components.SliderColors
import com.joohnq.moodapp.view.components.SliderComponents
import com.joohnq.moodapp.view.components.VerticalSlider
import com.joohnq.moodapp.view.components.VerticalSpacer
import com.joohnq.moodapp.view.constants.Colors
import com.joohnq.moodapp.view.routes.onNavigateToMedicationsSupplements
import com.joohnq.moodapp.viewmodel.OnboardingViewModel
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.sleep_quality_title

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingSleepQualityScreenUI(
    onGoBack: () -> Unit = {},
    snackBarState: SnackbarHostState = SnackbarHostState(),
    sliderValue: Float,
    setSliderValue: (Float) -> Unit = {},
    sleepQualityOptions: List<SleepQuality>,
    selectedSleepQuality: SleepQuality,
    onAction: () -> Unit = {}
) {
    val moods = remember { Mood.getAll().reversed() }
    OnboardingBaseComponent(
        page = 4,
        snackBarState = snackBarState,
        title = Res.string.sleep_quality_title,
        onGoBack = onGoBack,
        onContinue = onAction
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            VerticalSpacer(20.dp)
            Row(
                modifier = Modifier.fillMaxSize().height(400.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight().weight(1f).padding(vertical = 15.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    sleepQualityOptions.forEach { sleepQuality: SleepQuality ->
                        val textColor =
                            if (selectedSleepQuality == sleepQuality) Colors.Brown80 else Colors.Brown100Alpha64

                        DoubleText(
                            firstText = sleepQuality.firstText,
                            secondText = sleepQuality.secondText,
                            color = textColor
                        )
                    }
                }
                VerticalSlider(
                    modifier = Modifier.weight(1f).testTag(TestConstants.SLEEP_QUALITY_SLIDER),
                    sliderValue = sliderValue,
                    setSliderValue = setSliderValue,
                    thumb = { SliderComponents.SleepQualityThumb() },
                    track = { SliderComponents.SleepQualityTrack(it) },
                    sliderColors = SliderColors()
                )
                Column(
                    modifier = Modifier.fillMaxHeight().weight(1f).padding(vertical = 15.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.End
                ) {
                    moods.forEach { mood ->
                        Column {
                            MoodFace(
                                modifier = Modifier.size(48.dp),
                                mood,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun OnboardingSleepQualityScreen(
    navigation: NavController,
    onboardingViewModel: OnboardingViewModel = sharedViewModel(),
) {
    val onboardingState by onboardingViewModel.onboardingState.collectAsState()
    var sliderValue by rememberSaveable { mutableStateOf(0f) }
    val sleepQualityOptions: List<SleepQuality> = remember { SleepQuality.getAll() }
    val snackBarState = remember { SnackbarHostState() }

    OnboardingSleepQualityScreenUI(
        snackBarState = snackBarState,
        sliderValue = sliderValue,
        setSliderValue = {
            sliderValue = it
            val i = sliderValue.toInt() / 25
            onboardingViewModel.updateSleepQuality(
                sleepQualityOptions[sleepQualityOptions.size - i - 1]
            )
        },
        sleepQualityOptions = sleepQualityOptions,
        selectedSleepQuality = onboardingState.sleepQuality,
        onGoBack = navigation::popBackStack,
        onAction = navigation::onNavigateToMedicationsSupplements
    )
}

@Preview
@Composable
fun OnboardingSleepQualityScreenPreview() {
    OnboardingSleepQualityScreenUI(
        sliderValue = 100f,
        sleepQualityOptions = SleepQuality.getAll(),
        selectedSleepQuality = SleepQuality.Worst,
    )
}
