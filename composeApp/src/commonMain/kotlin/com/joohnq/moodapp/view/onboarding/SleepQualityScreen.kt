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
import androidx.compose.material3.Text
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
import com.joohnq.moodapp.view.components.TextStyles
import com.joohnq.moodapp.view.components.VerticalSlider
import com.joohnq.moodapp.view.entities.DoubleTextOption
import com.joohnq.moodapp.view.entities.Mood
import com.joohnq.moodapp.view.entities.MoodSaver
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.excellent
import moodapp.composeapp.generated.resources.fair
import moodapp.composeapp.generated.resources.five_hours
import moodapp.composeapp.generated.resources.good
import moodapp.composeapp.generated.resources.minus_three_hours
import moodapp.composeapp.generated.resources.poor
import moodapp.composeapp.generated.resources.seven_nine_hours
import moodapp.composeapp.generated.resources.six_seven_hours
import moodapp.composeapp.generated.resources.sleep_quality_title
import moodapp.composeapp.generated.resources.three_four_hours
import moodapp.composeapp.generated.resources.worst
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

class SleepQualityScreen : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val moods =
            rememberSaveable {
                listOf(
                    Mood.Overjoyed,
                    Mood.Happy,
                    Mood.Neutral,
                    Mood.Sad,
                    Mood.Depressed
                )
            }
        var selectedMood by rememberSaveable(stateSaver = MoodSaver) { mutableStateOf(Mood.Depressed) }
        var sliderValue by rememberSaveable { mutableStateOf(0f) }
        val textOption = remember {
            listOf(
                DoubleTextOption(
                    firstText = Res.string.excellent,
                    secondText = Res.string.seven_nine_hours,
                ),
                DoubleTextOption(
                    firstText = Res.string.good,
                    secondText = Res.string.six_seven_hours,
                ),
                DoubleTextOption(
                    firstText = Res.string.fair,
                    secondText = Res.string.five_hours,
                ),
                DoubleTextOption(
                    firstText = Res.string.poor,
                    secondText = Res.string.three_four_hours,
                ),
                DoubleTextOption(
                    firstText = Res.string.worst,
                    secondText = Res.string.minus_three_hours,
                ),
            )
        }

        LaunchedEffect(sliderValue) {
            val i = sliderValue.toInt() / 25
            selectedMood = moods[moods.size - i - 1]
        }

        OnboardingBaseComponent(
            page = 4,
            title = Res.string.sleep_quality_title,
            isContinueButtonVisible = true,
            onContinue = { navigator.push(MedicationsSupplementsScreen()) },
            onBack = { navigator.pop() }
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxSize().height(400.dp)
            ) {
                val textColor = if (selectedMood != Mood.Overjoyed) Colors.Alpha48 else Colors.Brown80

                Column(
                    modifier = Modifier.fillMaxHeight().weight(1f).padding(vertical = 15.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    textOption.forEach {
                        DoubleText(
                            firstText = it.firstText,
                            secondText = it.secondText,
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