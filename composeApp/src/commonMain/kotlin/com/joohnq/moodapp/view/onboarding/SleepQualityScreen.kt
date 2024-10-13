package com.joohnq.moodapp.view.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
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
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.joohnq.moodapp.Colors
import com.joohnq.moodapp.view.components.TextStyles
import com.joohnq.moodapp.view.components.MoodFace
import com.joohnq.moodapp.view.components.SliderComponents
import com.joohnq.moodapp.view.components.VerticalSlider
import com.joohnq.moodapp.view.entities.Mood
import com.joohnq.moodapp.view.entities.moods
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
import org.jetbrains.compose.resources.stringResource

class SleepQualityScreen : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
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

        OnboardingBaseComponent(
            page = 4,
            title = Res.string.sleep_quality_title,
            onContinue = { navigator.push(MedicationsSupplementsScreen()) },
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 50.dp)
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight().weight(1f).padding(vertical = 15.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            stringResource(Res.string.excellent),
                            style = TextStyles.OnboardingSleepQualityTitle()
                                .copy(color = if (selectedMood != Mood.Overjoyed) Colors.Alpha48 else Colors.Brown80)
                        )
                        Text(
                            stringResource(Res.string.seven_nine_hours),
                            style = TextStyles.OnboardingSleepQualitySubTitle()
                                .copy(color = if (selectedMood != Mood.Overjoyed) Colors.Alpha48 else Colors.Brown80)
                        )
                    }
                    Column {
                        Text(
                            stringResource(Res.string.good),
                            style = TextStyles.OnboardingSleepQualityTitle()
                                .copy(color = if (selectedMood != Mood.Happy) Colors.Alpha48 else Colors.Brown80)
                        )
                        Text(
                            stringResource(Res.string.six_seven_hours),
                            style = TextStyles.OnboardingSleepQualitySubTitle()
                                .copy(color = if (selectedMood != Mood.Happy) Colors.Alpha48 else Colors.Brown80)
                        )
                    }
                    Column {
                        Text(
                            stringResource(Res.string.fair),
                            style = TextStyles.OnboardingSleepQualityTitle()
                                .copy(color = if (selectedMood != Mood.Neutral) Colors.Alpha48 else Colors.Brown80)
                        )
                        Text(
                            stringResource(Res.string.five_hours),
                            style = TextStyles.OnboardingSleepQualitySubTitle()
                                .copy(color = if (selectedMood != Mood.Neutral) Colors.Alpha48 else Colors.Brown80)
                        )
                    }
                    Column {
                        Text(
                            stringResource(Res.string.poor),
                            style = TextStyles.OnboardingSleepQualityTitle()
                                .copy(color = if (selectedMood != Mood.Sad) Colors.Alpha48 else Colors.Brown80)
                        )
                        Text(
                            stringResource(Res.string.three_four_hours),
                            style = TextStyles.OnboardingSleepQualitySubTitle()
                                .copy(color = if (selectedMood != Mood.Sad) Colors.Alpha48 else Colors.Brown80)
                        )
                    }
                    Column {
                        Text(
                            stringResource(Res.string.worst),
                            style = TextStyles.OnboardingSleepQualityTitle()
                                .copy(color = if (selectedMood != Mood.Depressed) Colors.Alpha48 else Colors.Brown80)
                        )
                        Text(
                            stringResource(Res.string.minus_three_hours),
                            style = TextStyles.OnboardingSleepQualitySubTitle()
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