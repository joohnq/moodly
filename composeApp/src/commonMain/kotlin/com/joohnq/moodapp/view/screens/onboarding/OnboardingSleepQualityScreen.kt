package com.joohnq.moodapp.view.screens.onboarding

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joohnq.moodapp.constants.TestConstants
import com.joohnq.moodapp.entities.Mood
import com.joohnq.moodapp.entities.SleepQuality
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.view.NextAndBackAction
import com.joohnq.moodapp.view.components.DoubleText
import com.joohnq.moodapp.view.components.MoodFace
import com.joohnq.moodapp.view.components.SleepQualityThumb
import com.joohnq.moodapp.view.components.SleepQualityTrack
import com.joohnq.moodapp.view.components.VerticalSlider
import com.joohnq.moodapp.view.components.VerticalSpacer
import com.joohnq.moodapp.view.routes.onNavigateToMedicationsSupplements
import com.joohnq.moodapp.view.ui.Colors
import com.joohnq.moodapp.view.ui.ComponentColors
import com.joohnq.moodapp.view.ui.PaddingModifier.Companion.paddingVerticalLarge
import com.joohnq.moodapp.viewmodel.OnboardingIntent
import com.joohnq.moodapp.viewmodel.OnboardingViewModel
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.sleep_quality_title

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingSleepQualityScreenUI(
    sliderValue: Float,
    selectedSleepQuality: SleepQuality,
    onNavigation: (NextAndBackAction) -> Unit = {},
    onAction: (OnboardingIntent) -> Unit = {}
) {
    val moods = remember { Mood.getAll().reversed() }
    val sleepQualityOptions: List<SleepQuality> = remember { SleepQuality.getAll() }

    OnboardingBaseComponent(
        page = 4,
        title = Res.string.sleep_quality_title,
        onAction = onNavigation
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            VerticalSpacer(20.dp)
            Row(
                modifier = Modifier.fillMaxSize().height(400.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight().weight(1f).paddingVerticalLarge(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    sleepQualityOptions.forEach { sleepQuality: SleepQuality ->
                        DoubleText(
                            firstText = sleepQuality.firstText,
                            secondText = sleepQuality.secondText,
                            color = if (selectedSleepQuality == sleepQuality) Colors.Brown80 else Colors.Brown100Alpha64
                        )
                    }
                }
                VerticalSlider(
                    modifier = Modifier.weight(1f).testTag(TestConstants.SLEEP_QUALITY_SLIDER),
                    sliderValue = sliderValue,
                    setSliderValue = {
                        onAction(OnboardingIntent.UpdateSliderValue(it))
                        onAction(OnboardingIntent.UpdateSleepQuality(SleepQuality.fromSliderValue(it)))
                    },
                    thumb = { SleepQualityThumb() },
                    track = { SleepQualityTrack(it) },
                    sliderColors = ComponentColors.Slider.SleepQualitySliderColors()
                )
                Column(
                    modifier = Modifier.fillMaxHeight().weight(1f).paddingVerticalLarge(),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.End
                ) {
                    moods.forEach { mood ->
                        Column {
                            MoodFace(
                                modifier = Modifier.size(48.dp),
                                mood = mood,
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

    OnboardingSleepQualityScreenUI(
        sliderValue = onboardingState.sliderValue,
        selectedSleepQuality = onboardingState.sleepQuality,
        onAction = onboardingViewModel::onAction,
        onNavigation = { action ->
            when (action) {
                NextAndBackAction.OnContinue -> navigation.onNavigateToMedicationsSupplements()
                NextAndBackAction.OnGoBack -> navigation.popBackStack()
            }
        }
    )
}

@Preview
@Composable
fun OnboardingSleepQualityScreenPreview() {
    OnboardingSleepQualityScreenUI(
        sliderValue = 0f,
        selectedSleepQuality = SleepQuality.Worst,
    )
}
