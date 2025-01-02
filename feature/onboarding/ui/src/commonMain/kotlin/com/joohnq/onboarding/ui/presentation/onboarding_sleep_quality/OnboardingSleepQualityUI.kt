package com.joohnq.onboarding.ui.presentation.onboarding_sleep_quality

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
import com.joohnq.mood.domain.entity.Mood
import com.joohnq.mood.ui.MoodResource.Companion.toResource
import com.joohnq.mood.ui.components.MoodFace
import com.joohnq.onboarding.ui.presentation.OnboardingBaseComponent
import com.joohnq.onboarding.ui.presentation.onboarding_sleep_quality.event.OnboardingSleepQualityEvent
import com.joohnq.onboarding.ui.presentation.onboarding_sleep_quality.state.OnboardingSleepQualityState
import com.joohnq.onboarding.ui.viewmodel.OnboardingViewModelIntent
import com.joohnq.shared.ui.Res
import com.joohnq.shared.ui.components.DoubleText
import com.joohnq.shared.ui.components.SleepQualityThumb
import com.joohnq.shared.ui.components.SleepQualityTrack
import com.joohnq.shared.ui.components.VerticalSlider
import com.joohnq.shared.ui.components.VerticalSpacer
import com.joohnq.shared.ui.sleep_quality_title
import com.joohnq.shared.ui.theme.Colors
import com.joohnq.shared.ui.theme.ComponentColors
import com.joohnq.shared.ui.theme.PaddingModifier.Companion.paddingVerticalLarge
import com.joohnq.sleep_quality.domain.entity.SleepQuality
import com.joohnq.sleep_quality.ui.SleepQualityResource.Companion.toResource

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
                        val resource = sleepQuality.toResource()
                        DoubleText(
                            firstText = resource.firstText,
                            secondText = resource.secondText,
                            color = if (state.selectedSleepQuality == sleepQuality) Colors.Brown80 else Colors.Brown100Alpha64
                        )
                    }
                }
                VerticalSlider(
                    modifier = Modifier.weight(1f)
                        .testTag(OnboardingSleepQualityScreen.OnboardingSleepQualityTestTag.SLEEP_QUALITY_SLIDER),
                    sliderValue = state.sliderValue,
                    setSliderValue = {
                        state.onAction(OnboardingViewModelIntent.UpdateSliderValue(it))
                        state.onAction(
                            OnboardingViewModelIntent.UpdateSleepQuality(
                                SleepQuality.fromSliderValue(it)
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
                    moods.forEach { mood: Mood ->
                        val resource = mood.toResource()
                        Column {
                            MoodFace(
                                modifier = Modifier.size(48.dp),
                                mood = resource,
                            )
                        }
                    }
                }
            }
        }
    }
}