package com.joohnq.moodapp.onboarding

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SliderColors
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
import com.joohnq.moodapp.Colors
import com.joohnq.moodapp.components.CustomTextStyle
import com.joohnq.moodapp.components.MoodFace
import com.joohnq.moodapp.components.SliderComponents
import com.joohnq.moodapp.components.VerticalSlider
import com.joohnq.moodapp.entities.Mood
import com.joohnq.moodapp.entities.moods
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.excellent
import moodapp.composeapp.generated.resources.fair
import moodapp.composeapp.generated.resources.five_hours
import moodapp.composeapp.generated.resources.poor
import moodapp.composeapp.generated.resources.good
import moodapp.composeapp.generated.resources.minus_three_hours
import moodapp.composeapp.generated.resources.seven_nine_hours
import moodapp.composeapp.generated.resources.six_seven_hours
import moodapp.composeapp.generated.resources.sleep_quality_title
import moodapp.composeapp.generated.resources.three_four_hours
import moodapp.composeapp.generated.resources.worst
import org.jetbrains.compose.resources.stringResource

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
                    stringResource(Res.string.sleep_quality_title),
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
                                stringResource(Res.string.excellent),
                                style = CustomTextStyle.TextStyleOnboardingSleepQualityTitle()
                                    .copy(color = if (selectedMood != Mood.Overjoyed) Colors.Alpha48 else Colors.Brown80)
                            )
                            Text(
                                stringResource(Res.string.seven_nine_hours),
                                style = CustomTextStyle.TextStyleOnboardingSleepQualitySubTitle()
                                    .copy(color = if (selectedMood != Mood.Overjoyed) Colors.Alpha48 else Colors.Brown80)
                            )
                        }
                        Column {
                            Text(
                                stringResource(Res.string.good),
                                style = CustomTextStyle.TextStyleOnboardingSleepQualityTitle()
                                    .copy(color = if (selectedMood != Mood.Happy) Colors.Alpha48 else Colors.Brown80)
                            )
                            Text(
                                stringResource(Res.string.six_seven_hours),
                                style = CustomTextStyle.TextStyleOnboardingSleepQualitySubTitle()
                                    .copy(color = if (selectedMood != Mood.Happy) Colors.Alpha48 else Colors.Brown80)
                            )
                        }
                        Column {
                            Text(
                                stringResource(Res.string.fair),
                                style = CustomTextStyle.TextStyleOnboardingSleepQualityTitle()
                                    .copy(color = if (selectedMood != Mood.Neutral) Colors.Alpha48 else Colors.Brown80)
                            )
                            Text(
                                stringResource(Res.string.five_hours),
                                style = CustomTextStyle.TextStyleOnboardingSleepQualitySubTitle()
                                    .copy(color = if (selectedMood != Mood.Neutral) Colors.Alpha48 else Colors.Brown80)
                            )
                        }
                        Column {
                            Text(
                                stringResource(Res.string.poor),
                                style = CustomTextStyle.TextStyleOnboardingSleepQualityTitle()
                                    .copy(color = if (selectedMood != Mood.Sad) Colors.Alpha48 else Colors.Brown80)
                            )
                            Text(
                                stringResource(Res.string.three_four_hours),
                                style = CustomTextStyle.TextStyleOnboardingSleepQualitySubTitle()
                                    .copy(color = if (selectedMood != Mood.Sad) Colors.Alpha48 else Colors.Brown80)
                            )
                        }
                        Column {
                            Text(
                                stringResource(Res.string.worst),
                                style = CustomTextStyle.TextStyleOnboardingSleepQualityTitle()
                                    .copy(color = if (selectedMood != Mood.Depressed) Colors.Alpha48 else Colors.Brown80)
                            )
                            Text(
                                stringResource(Res.string.minus_three_hours),
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