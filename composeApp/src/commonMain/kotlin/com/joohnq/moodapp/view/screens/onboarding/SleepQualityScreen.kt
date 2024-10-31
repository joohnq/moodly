package com.joohnq.moodapp.view.screens.onboarding

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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.joohnq.moodapp.constants.TestConstants
import com.joohnq.moodapp.entities.Mood
import com.joohnq.moodapp.entities.SleepQuality
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.view.components.DoubleText
import com.joohnq.moodapp.view.components.MoodFace
import com.joohnq.moodapp.view.components.SliderColors
import com.joohnq.moodapp.view.components.SliderComponents
import com.joohnq.moodapp.view.components.VerticalSlider
import com.joohnq.moodapp.view.constants.Colors
import com.joohnq.moodapp.view.routes.onNavigateToMedicationsSupplements
import com.joohnq.moodapp.viewmodel.MoodsViewModel
import com.joohnq.moodapp.viewmodel.OnboardingViewModel
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.sleep_quality_title
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SleepQualityScreen(
    navigation: NavController = rememberNavController(),
    onboardingViewModel: OnboardingViewModel = sharedViewModel(),
) {
    val moods = remember { Mood.getAll().reversed() }
    var selectedSleepQuality: SleepQuality by rememberSaveable(stateSaver = SleepQuality.getSaver()) {
        mutableStateOf(
            SleepQuality.Worst
        )
    }
    var sliderValue by rememberSaveable { mutableStateOf(0f) }
    val sleepQualityOption: List<SleepQuality> = remember { SleepQuality.getAll() }

    LaunchedEffect(sliderValue) {
        val i = sliderValue.toInt() / 25
        selectedSleepQuality = sleepQualityOption[sleepQualityOption.size - i - 1]
    }

    OnboardingBaseComponent(
        page = 4,
        title = Res.string.sleep_quality_title,
        isContinueButtonVisible = true,
        onBack = navigation::popBackStack,
        onContinue = {
            onboardingViewModel.setStatsRecordSleepQuality(selectedSleepQuality)
            navigation.onNavigateToMedicationsSupplements()
        },
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
