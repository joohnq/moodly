package com.joohnq.stress_level.add.presentation

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
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.add_stress_level
import com.joohnq.shared_resources.components.AppTopBar
import com.joohnq.shared_resources.components.button.PrimaryButton
import com.joohnq.shared_resources.components.layout.AppScaffoldLayout
import com.joohnq.shared_resources.components.slider.AppVerticalSlider
import com.joohnq.shared_resources.components.slider.AppVerticalSliderThump
import com.joohnq.shared_resources.components.slider.AppVerticalSliderTrack
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.continue_word
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.ComponentColors
import com.joohnq.shared_resources.theme.PaddingModifier.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.shared_resources.whats_your_stress_level_today
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddStressLevelScreenContent(
    snackBarState: SnackbarHostState = rememberSnackBarState(),
    state: AddStressLevelContract.State,
    onIntent: (AddStressLevelContract.Intent) -> Unit = {},
    onEvent: (AddStressLevelContract.Event) -> Unit = {},
) {
    AppScaffoldLayout(
        snackBarHostState = snackBarState,
        containerColor = Colors.Brown10,
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        BoxWithConstraints {
            val height = maxHeight * 0.5f
            Column(
                modifier =
                    Modifier
                        .padding(padding)
                        .paddingHorizontalMedium()
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AppTopBar(
                    text = Res.string.add_stress_level,
                    onGoBack = { onEvent(AddStressLevelContract.Event.GoBack) }
                )
                VerticalSpacer(60.dp)
                Text(
                    text = stringResource(Res.string.whats_your_stress_level_today),
                    style = TextStyles.headingSmExtraBold(),
                    color = Colors.Brown80,
                    textAlign = TextAlign.Center
                )
                VerticalSpacer(24.dp)
                Row(
                    modifier = Modifier.paddingHorizontalMedium().fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    AppVerticalSlider(
                        modifier = Modifier.height(height),
                        sliderValue = state.sliderValue,
                        setSliderValue = {
                            onIntent(AddStressLevelContract.Intent.ChangeStressLevel(it))
                        },
                        thumb = { AppVerticalSliderThump() },
                        track = { AppVerticalSliderTrack(it) },
                        sliderColors = ComponentColors.Slider.sleepQualitySliderColors()
                    )
                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text =
                                state.record.stressLevel.level
                                    .toString(),
                            style = TextStyles.displayLgExtraBold(),
                            color = Colors.Brown80
                        )
                        Text(
                            text = stringResource(state.record.stressLevel.text),
                            style = TextStyles.textXlBold(),
                            color = Colors.Brown100Alpha64,
                            textAlign = TextAlign.End
                        )
                    }
                }
                VerticalSpacer(24.dp)
                PrimaryButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = Res.string.continue_word,
                    onClick = { onIntent(AddStressLevelContract.Intent.Add) }
                )
            }
        }
    }
}