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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.mood.ui.components.MoodFace
import com.joohnq.mood.ui.resource.mapper.getAllMoodResource
import com.joohnq.onboarding.ui.presentation.OnboardingBaseComponent
import com.joohnq.onboarding.ui.viewmodel.OnboardingContract
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.SleepQualityThumb
import com.joohnq.shared_resources.components.SleepQualityTrack
import com.joohnq.shared_resources.components.VerticalSlider
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.sleep_quality_title
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.ComponentColors
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingVerticalLarge
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.sleep_quality.ui.resource.mapper.getAllSleepQualityResource
import com.joohnq.sleep_quality.ui.resource.mapper.toSleepQualityResource
import com.joohnq.sleep_quality.ui.resource.SleepQualityResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingSleepQualityUI(
    state: OnboardingContract.State,
    onEvent: (OnboardingContract.Event) -> Unit = {},
    onIntent: (OnboardingContract.Intent) -> Unit = {},
) {
    val moods = remember { getAllMoodResource().reversed() }
    val sleepQualityOptions: List<SleepQualityResource> = remember { getAllSleepQualityResource() }

    OnboardingBaseComponent(
        page = 4,
        title = Res.string.sleep_quality_title,
        onGoBack = { onEvent(OnboardingContract.Event.GoBack) },
        onContinue = { onEvent(OnboardingContract.Event.OnContinue) }
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
                    sleepQualityOptions.forEach { sleepQuality: SleepQualityResource ->
                        val color =
                            if (state.sleepQuality.sleepQuality == sleepQuality) Colors.Brown80 else Colors.Brown100Alpha64
                        Column {
                            Text(
                                stringResource(sleepQuality.firstText),
                                style = TextStyles.textLgExtraBold()
                                    .copy(color = color)
                            )
                            Text(
                                stringResource(sleepQuality.secondText),
                                style = TextStyles.labelSm()
                                    .copy(color = color)
                            )
                        }
                    }
                }
                VerticalSlider(
                    modifier = Modifier.weight(1f),
                    sliderValue = state.sliderValue,
                    setSliderValue = {
                        onIntent(OnboardingContract.Intent.UpdateSliderValue(it))
                        onIntent(
                            OnboardingContract.Intent.UpdateSleepQuality(
                                it.toSleepQualityResource()
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
                                resource = mood,
                            )
                        }
                    }
                }
            }
        }
    }
}