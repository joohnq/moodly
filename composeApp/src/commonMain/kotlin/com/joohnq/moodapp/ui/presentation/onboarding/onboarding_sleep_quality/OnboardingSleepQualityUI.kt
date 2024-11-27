package com.joohnq.moodapp.ui.presentation.onboarding.onboarding_sleep_quality

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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.domain.Mood
import com.joohnq.moodapp.domain.SleepQuality
import com.joohnq.moodapp.ui.components.DoubleText
import com.joohnq.moodapp.ui.components.MoodFace
import com.joohnq.moodapp.ui.components.SleepQualityThumb
import com.joohnq.moodapp.ui.components.SleepQualityTrack
import com.joohnq.moodapp.ui.components.VerticalSlider
import com.joohnq.moodapp.ui.components.VerticalSpacer
import com.joohnq.moodapp.ui.presentation.onboarding.OnboardingBaseComponent
import com.joohnq.moodapp.ui.presentation.onboarding.onboarding_sleep_quality.event.OnboardingSleepQualityEvent
import com.joohnq.moodapp.ui.presentation.onboarding.onboarding_sleep_quality.state.OnboardingSleepQualityState
import com.joohnq.moodapp.ui.theme.Colors
import com.joohnq.moodapp.ui.theme.ComponentColors
import com.joohnq.moodapp.ui.theme.PaddingModifier.Companion.paddingVerticalLarge
import com.joohnq.moodapp.ui.presentation.onboarding.OnboardingIntent
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.sleep_quality_title

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingSleepQualityUI(
    state: OnboardingSleepQualityState,
) {
    val moods = remember { Mood.getAll().reversed() }
    val sleepQualityOptions: List<SleepQuality> = remember { SleepQuality.getAll() }

    OnboardingBaseComponent(
        page = 4,
        title = Res.string.sleep_quality_title,
        onGoBack = { state.onEvent(OnboardingSleepQualityEvent.OnGoBack) },
        onContinue = { state.onEvent(OnboardingSleepQualityEvent.OnNavigateToOnboardingMedicationSupplementsScreen) }
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
                            color = if (state.selectedSleepQuality == sleepQuality) Colors.Brown80 else Colors.Brown100Alpha64
                        )
                    }
                }
                VerticalSlider(
                    modifier = Modifier.weight(1f)
                        .testTag(OnboardingSleepQualityScreen.OnboardingSleepQualityTestTag.SLEEP_QUALITY_SLIDER),
                    sliderValue = state.sliderValue,
                    setSliderValue = {
                        state.onAction(OnboardingIntent.UpdateSliderValue(it))
                        state.onAction(
                            OnboardingIntent.UpdateSleepQuality(
                                SleepQuality.fromSliderValue(
                                    it
                                )
                            )
                        )
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