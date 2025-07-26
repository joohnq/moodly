package com.joohnq.onboarding.impl.ui.presentation.onboarding_sleep_quality

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
import com.joohnq.mood.impl.ui.components.MoodFace
import com.joohnq.mood.impl.ui.mapper.getAllMoodResource
import com.joohnq.onboarding.impl.ui.event.OnboardingEvent
import com.joohnq.onboarding.impl.ui.viewmodel.OnboardingContract
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.slider.AppVerticalSlider
import com.joohnq.shared_resources.components.slider.AppVerticalSliderThump
import com.joohnq.shared_resources.components.slider.AppVerticalSliderTrack
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.sleep_quality_title
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.ComponentColors
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingVerticalLarge
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.sleep_quality.impl.ui.mapper.getAllSleepQualityResource
import com.joohnq.sleep_quality.impl.ui.mapper.toSleepQualityResource
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardingSleepQualityContent(
    state: SleepQualityRecordResource,
    sliderValue: Float,
    onEvent: (OnboardingEvent) -> Unit = {},
    onAction: (OnboardingContract.Intent) -> Unit = {},
) {
    val moods = remember { getAllMoodResource().reversed() }
    val sleepQualityOptions: List<SleepQualityResource> = remember { getAllSleepQualityResource() }

    _root_ide_package_.com.joohnq.onboarding.impl.ui.presentation.OnboardingBaseComponent(
        page = 4,
        title = Res.string.sleep_quality_title,
        onGoBack = { onEvent(OnboardingEvent.OnGoBack) },
        onContinue = { onEvent(OnboardingEvent.OnNavigateToNext) }
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
                    sleepQualityOptions.forEach { sleepQualityOption: SleepQualityResource ->
                        val color =
                            if (state == sleepQualityOption) Colors.Brown80 else Colors.Brown100Alpha64
                        Column {
                            Text(
                                stringResource(sleepQualityOption.firstText),
                                style =
                                    TextStyles
                                        .textLgExtraBold()
                                        .copy(color = color)
                            )
                            Text(
                                stringResource(sleepQualityOption.secondText),
                                style =
                                    TextStyles
                                        .labelSm()
                                        .copy(color = color)
                            )
                        }
                    }
                }
                AppVerticalSlider(
                    modifier = Modifier.weight(1f),
                    sliderValue = sliderValue,
                    setSliderValue = {
                        onAction(OnboardingContract.Intent.UpdateSliderValue(it))
                        onAction(
                            OnboardingContract.Intent.UpdateSleepQuality(
                                it.toSleepQualityResource()
                            )
                        )
                    },
                    thumb = { AppVerticalSliderThump() },
                    track = { AppVerticalSliderTrack(it) },
                    sliderColors = ComponentColors.Slider.sleepQualitySliderColors()
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
                                resource = mood
                            )
                        }
                    }
                }
            }
        }
    }
}
