package com.joohnq.moodapp.view.screens.sleepquality

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.joohnq.moodapp.view.components.ButtonWithArrowRight
import com.joohnq.moodapp.view.components.DoubleText
import com.joohnq.moodapp.view.components.MoodFace
import com.joohnq.moodapp.view.components.SliderColors
import com.joohnq.moodapp.view.components.SliderComponents
import com.joohnq.moodapp.view.components.VerticalSlider
import com.joohnq.moodapp.view.constants.Colors
import com.joohnq.moodapp.viewmodel.SleepQualityViewModel
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.continue_word

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SleepQualityScreenUI(
    modifier: Modifier = Modifier.fillMaxSize(),
    isContinueButtonVisible: Boolean,
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
    selectedSleepQuality: SleepQuality,
    sliderValue: Float,
    sleepQualityOption: List<SleepQuality>,
    setSliderValue: (Float) -> Unit,
    onContinue: () -> Unit
) {
    val moods = remember { Mood.getAll().reversed() }

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxSize().height(400.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight().weight(1f).padding(vertical = 15.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    sleepQualityOption.forEach { sleepQuality: SleepQuality ->
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
            Spacer(modifier = Modifier.height(20.dp))
            if (isContinueButtonVisible) {
                ButtonWithArrowRight(
                    modifier = Modifier.fillMaxWidth().testTag(TestConstants.CONTINUE_BUTTON),
                    text = Res.string.continue_word,
                    onClick = onContinue
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun SleepQualityScreen(
    sleepQualityViewModel: SleepQualityViewModel = sharedViewModel(),
    navigation: NavController
) {
    var selectedSleepQuality: SleepQuality by rememberSaveable(stateSaver = SleepQuality.getSaver()) {
        mutableStateOf(
            SleepQuality.Worst
        )
    }
    val snackBarHostState = remember { SnackbarHostState() }
    var sliderValue by rememberSaveable { mutableStateOf(0f) }
    val sleepQualityOption: List<SleepQuality> = remember { SleepQuality.getAll() }

    LaunchedEffect(sliderValue) {
        val i = sliderValue.toInt() / 25
        selectedSleepQuality = sleepQualityOption[sleepQualityOption.size - i - 1]
    }

    SleepQualityScreenUI(
        isContinueButtonVisible = true,
        snackBarHostState = snackBarHostState,
        selectedSleepQuality = selectedSleepQuality,
        sliderValue = sliderValue,
        sleepQualityOption = sleepQualityOption,
        setSliderValue = { sliderValue = it },
        onContinue = {
        }
    )
}
