package com.joohnq.moodapp.ui.presentation.add_stress_level

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.ui.components.ContinueButton
import com.joohnq.moodapp.ui.components.SleepQualityThumb
import com.joohnq.moodapp.ui.components.SleepQualityTrack
import com.joohnq.moodapp.ui.components.TopBar
import com.joohnq.moodapp.ui.components.VerticalSlider
import com.joohnq.moodapp.ui.components.VerticalSpacer
import com.joohnq.moodapp.ui.presentation.add_stress_level.event.AddStressLevelEvent
import com.joohnq.moodapp.ui.presentation.add_stress_level.state.AddStressLevelState
import com.joohnq.moodapp.ui.theme.Colors
import com.joohnq.moodapp.ui.theme.ComponentColors
import com.joohnq.moodapp.ui.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.moodapp.ui.theme.TextStyles
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.add_stress_level
import moodapp.composeapp.generated.resources.whats_your_stress_level_today
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddStressLevelScreenUI(
    state: AddStressLevelState,
) {
    Scaffold(
        snackbarHost = { SnackbarHost(state.snackBarState) },
        containerColor = Colors.Brown10,
        modifier = Modifier.fillMaxSize(),
    ) { padding ->
        BoxWithConstraints {
            val height = maxHeight * 0.5f
            Column(
                modifier = Modifier.padding(padding).paddingHorizontalMedium().fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TopBar(
                    text = Res.string.add_stress_level,
                    onGoBack = { state.onEvent(AddStressLevelEvent.OnGoBack) }
                )
                VerticalSpacer(60.dp)
                Text(
                    text = stringResource(Res.string.whats_your_stress_level_today),
                    style = TextStyles.HeadingSmExtraBold(),
                    color = Colors.Brown80,
                    textAlign = TextAlign.Center
                )
                VerticalSpacer(24.dp)
                Row(
                    modifier = Modifier.paddingHorizontalMedium().fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    VerticalSlider(
                        modifier = Modifier.height(height),
                        sliderValue = state.addStressLevelViewModelState.sliderValue,
                        setSliderValue = {
                            state.onAddAction(AddStressLevelIntent.UpdateAddingSliderValue(it))
                        },
                        thumb = { SleepQualityThumb() },
                        track = { SleepQualityTrack(it) },
                        sliderColors = ComponentColors.Slider.SleepQualitySliderColors()
                    )
                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = state.addStressLevelViewModelState.stressLevel.level.toString(),
                            style = TextStyles.DisplayLgExtraBold(),
                            color = Colors.Brown80
                        )
                        Text(
                            text = stringResource(state.addStressLevelViewModelState.stressLevel.text),
                            style = TextStyles.TextXlBold(),
                            color = Colors.Brown100Alpha64,
                            textAlign = TextAlign.End
                        )
                    }
                }
                VerticalSpacer(24.dp)
                ContinueButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { state.onEvent(AddStressLevelEvent.OnContinue) }
                )
            }
        }
    }
}