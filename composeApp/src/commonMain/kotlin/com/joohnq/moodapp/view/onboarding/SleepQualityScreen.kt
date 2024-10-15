package com.joohnq.moodapp.view.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.joohnq.moodapp.Colors
import com.joohnq.moodapp.view.components.DoubleText
import com.joohnq.moodapp.view.components.MoodFace
import com.joohnq.moodapp.view.components.SliderColors
import com.joohnq.moodapp.view.components.SliderComponents
import com.joohnq.moodapp.view.components.VerticalSlider
import com.joohnq.moodapp.view.entities.Mood
import com.joohnq.moodapp.view.entities.SleepQuality
import com.joohnq.moodapp.view.entities.SleepQualitySaver
import com.joohnq.moodapp.viewmodel.MoodsViewModel
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.sleep_quality_title
import org.koin.compose.koinInject

class SleepQualityScreen : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val moods = remember { Mood.getAll().reversed() }
        val moodsViewModel: MoodsViewModel = koinInject()
        var selectedSleepQuality by rememberSaveable(stateSaver = SleepQualitySaver) { mutableStateOf(SleepQuality.Worst) }
        var sliderValue by rememberSaveable { mutableStateOf(0f) }
        val sleepQualityOption = remember { SleepQuality.getAll() }

        LaunchedEffect(sliderValue) {
            val i = sliderValue.toInt() / 25
            selectedSleepQuality = sleepQualityOption[sleepQualityOption.size - i - 1]
        }

        OnboardingBaseComponent(
            page = 4,
            title = Res.string.sleep_quality_title,
            isContinueButtonVisible = true,
            onContinue = {
                moodsViewModel.setCurrentMoodSleepQuality(selectedSleepQuality)
                navigator.push(MedicationsSupplementsScreen())
            },
            onBack = { navigator.pop() }
        ) {
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
                            if (selectedSleepQuality == sleepQuality) Colors.Brown80 else Colors.Alpha100

                        DoubleText(
                            firstText = sleepQuality.firstText,
                            secondText = sleepQuality.secondText,
                            color = textColor
                        )
                    }
                }
                VerticalSlider(
                    modifier = Modifier.weight(1f),
                    sliderValue = sliderValue,
                    setSliderValue = { sliderValue = it },
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
        }
    }
}