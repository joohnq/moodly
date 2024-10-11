package com.joohnq.moodapp.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.joohnq.moodapp.Colors
import com.joohnq.moodapp.components.CustomTextStyle
import com.joohnq.moodapp.components.MoodFace
import com.joohnq.moodapp.components.SliderComponents
import com.joohnq.moodapp.components.VerticalSlider
import com.joohnq.moodapp.entities.Mood
import com.joohnq.moodapp.entities.moods

@OptIn(ExperimentalMaterial3Api::class)
fun Modifier.progress(
    sliderState: SliderState,
    height: Dp,
    shape: Shape = CircleShape
) = this
    // Compute the fraction based on the slider's current value.
    // We do this by dividing the current value by the total value.
    // However, the start value might not always be 0, so we need to
    // subtract the start value from both the current value and the total value.
    .fillMaxWidth(fraction = (sliderState.value - sliderState.valueRange.start) / (sliderState.valueRange.endInclusive - sliderState.valueRange.start))
    .heightIn(min = height)
    .clip(shape)


class OnboardingSleepQuality : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        var selectedMood by remember { mutableStateOf<Mood>(Mood.Depressed) }
        var sliderValue by remember { mutableStateOf(0f) }

        val sliderColors = SliderColors(
            thumbColor = Colors.Orange40,
            activeTickColor = Colors.Orange40,
            inactiveTickColor = Colors.Brown20,
            activeTrackColor = Colors.Orange40,
            inactiveTrackColor = Colors.Brown20,
            disabledThumbColor = Colors.Brown20,
            disabledActiveTrackColor = Colors.Orange40,
            disabledActiveTickColor = Colors.Orange40,
            disabledInactiveTrackColor = Colors.Brown20,
            disabledInactiveTickColor = Colors.Brown20
        )

        LaunchedEffect(sliderValue) {
            val i = sliderValue.toInt() / 25
            selectedMood = moods[moods.size - i - 1]
        }

        Scaffold(
            containerColor = Colors.Brown10,
            modifier = Modifier.fillMaxSize()
        ) { padding ->
            Column(
                modifier = Modifier.padding(padding).padding(horizontal = 16.dp).fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OnboardingTopBar(4)
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    "How would you rate your sleep quality?",
                    style = CustomTextStyle.TextStyleOnboardingScreenTitle()
                )
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth().weight(1f).padding(vertical = 50.dp)
                        .fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier.fillMaxHeight().weight(1f).padding(vertical = 15.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                "Excellent",
                                style = CustomTextStyle.TextStyleOnboardingSleepQualityTitle()
                                    .copy(color = if (selectedMood != Mood.Overjoyed) Colors.Alpha48 else Colors.Brown80)
                            )
                            Text(
                                "7 - 9 HOURS",
                                style = CustomTextStyle.TextStyleOnboardingSleepQualitySubTitle()
                                    .copy(color = if (selectedMood != Mood.Overjoyed) Colors.Alpha48 else Colors.Brown80)
                            )
                        }
                        Column {
                            Text(
                                "Good",
                                style = CustomTextStyle.TextStyleOnboardingSleepQualityTitle()
                                    .copy(color = if (selectedMood != Mood.Happy) Colors.Alpha48 else Colors.Brown80)
                            )
                            Text(
                                "6 - 7 HOURS",
                                style = CustomTextStyle.TextStyleOnboardingSleepQualitySubTitle()
                                    .copy(color = if (selectedMood != Mood.Happy) Colors.Alpha48 else Colors.Brown80)
                            )
                        }
                        Column {
                            Text(
                                "Fair",
                                style = CustomTextStyle.TextStyleOnboardingSleepQualityTitle()
                                    .copy(color = if (selectedMood != Mood.Neutral) Colors.Alpha48 else Colors.Brown80)
                            )
                            Text(
                                "5 HOURS",
                                style = CustomTextStyle.TextStyleOnboardingSleepQualitySubTitle()
                                    .copy(color = if (selectedMood != Mood.Neutral) Colors.Alpha48 else Colors.Brown80)
                            )
                        }
                        Column {
                            Text(
                                "Poor",
                                style = CustomTextStyle.TextStyleOnboardingSleepQualityTitle()
                                    .copy(color = if (selectedMood != Mood.Sad) Colors.Alpha48 else Colors.Brown80)
                            )
                            Text(
                                "7 - 9 HOURS",
                                style = CustomTextStyle.TextStyleOnboardingSleepQualitySubTitle()
                                    .copy(color = if (selectedMood != Mood.Sad) Colors.Alpha48 else Colors.Brown80)
                            )
                        }
                        Column {
                            Text(
                                "Worst",
                                style = CustomTextStyle.TextStyleOnboardingSleepQualityTitle()
                                    .copy(color = if (selectedMood != Mood.Depressed) Colors.Alpha48 else Colors.Brown80)
                            )
                            Text(
                                "< 3 HOURS",
                                style = CustomTextStyle.TextStyleOnboardingSleepQualitySubTitle()
                                    .copy(color = if (selectedMood != Mood.Depressed) Colors.Alpha48 else Colors.Brown80)
                            )
                        }
                    }
                    VerticalSlider(
                        modifier = Modifier.weight(1f),
                        sliderValue = sliderValue,
                        setSliderValue = { sliderValue = it },
                        thumb = { SliderComponents.SleepQualityThumb() },
                        track = { SliderComponents.SleepQualityTrack(it) },
                        sliderColors = sliderColors
                    )
                    Column(
                        modifier = Modifier.fillMaxHeight().weight(1f).padding(vertical = 15.dp),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
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
}