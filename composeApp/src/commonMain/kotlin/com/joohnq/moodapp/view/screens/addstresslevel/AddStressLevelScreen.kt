package com.joohnq.moodapp.view.screens.addstresslevel

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
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joohnq.moodapp.entities.StressLevel
import com.joohnq.moodapp.entities.Stressor
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.view.NextAndBackAction
import com.joohnq.moodapp.view.components.ContinueButton
import com.joohnq.moodapp.view.components.SleepQualityThumb
import com.joohnq.moodapp.view.components.SleepQualityTrack
import com.joohnq.moodapp.view.components.TopBar
import com.joohnq.moodapp.view.components.VerticalSlider
import com.joohnq.moodapp.view.components.VerticalSpacer
import com.joohnq.moodapp.view.routes.onNavigateToStressLevelPopUp
import com.joohnq.moodapp.view.routes.onNavigateToStressStressors
import com.joohnq.moodapp.view.state.UiState.Companion.fold
import com.joohnq.moodapp.view.ui.Colors
import com.joohnq.moodapp.view.ui.ComponentColors
import com.joohnq.moodapp.view.ui.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.moodapp.view.ui.PaddingModifier.Companion.paddingHorizontalSmall
import com.joohnq.moodapp.view.ui.TextStyles
import com.joohnq.moodapp.viewmodel.StressLevelIntent
import com.joohnq.moodapp.viewmodel.StressLevelViewModel
import kotlinx.coroutines.launch
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.add_stress_level
import moodapp.composeapp.generated.resources.whats_your_stress_level_today
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddStressLevelScreenUI(
    snackBarState: SnackbarHostState = remember { SnackbarHostState() },
    selectedStressLevel: StressLevel,
    sliderValue: Float,
    onAction: (StressLevelIntent) -> Unit = {},
    onNavigation: (NextAndBackAction) -> Unit = {},
) {
    Scaffold(
        snackbarHost = { SnackbarHost(snackBarState) },
        containerColor = Colors.Brown10,
        modifier = Modifier.fillMaxSize(),
    ) { padding ->
        BoxWithConstraints {
            val height = maxHeight * 0.5f
            Column(
                modifier = Modifier.padding(padding).paddingHorizontalSmall().fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TopBar(
                    text = Res.string.add_stress_level,
                    onGoBack = { onNavigation(NextAndBackAction.OnGoBack) }
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
                        sliderValue = sliderValue,
                        setSliderValue = {
                            onAction(StressLevelIntent.UpdateAddingSliderValue(it))
                            onAction(StressLevelIntent.UpdateAddingStressLevel)
                        },
                        thumb = { SleepQualityThumb() },
                        track = { SleepQualityTrack(it) },
                        sliderColors = ComponentColors.Slider.SleepQualitySliderColors()
                    )
                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = selectedStressLevel.level.toString(),
                            style = TextStyles.DisplayLgExtraBold(),
                            color = Colors.Brown80
                        )
                        Text(
                            text = stringResource(selectedStressLevel.text),
                            style = TextStyles.TextXlBold(),
                            color = Colors.Brown100Alpha64,
                            textAlign = TextAlign.End
                        )
                    }
                }
                VerticalSpacer(24.dp)
                ContinueButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onNavigation(NextAndBackAction.OnContinue) }
                )
            }
        }
    }
}


@Composable
fun AddStressLevelScreen(
    navigation: NavController,
    stressLevelViewModel: StressLevelViewModel = sharedViewModel()
) {
    val snackBarState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val stressLevelState by stressLevelViewModel.stressLevelState.collectAsState()

    LaunchedEffect(stressLevelState.adding.status) {
        stressLevelState.adding.status.fold(
            onError = { error -> scope.launch { snackBarState.showSnackbar(error) } },
            onSuccess = {
                navigation.onNavigateToStressLevelPopUp()
                stressLevelViewModel.onAction(StressLevelIntent.ResetAdding)
            },
        )
    }

    AddStressLevelScreenUI(
        snackBarState = snackBarState,
        sliderValue = stressLevelState.adding.sliderValue,
        selectedStressLevel = stressLevelState.adding.stressLevel,
        onAction = stressLevelViewModel::onAction,
        onNavigation = { action ->
            when (action) {
                NextAndBackAction.OnContinue -> {
                    if (stressLevelState.adding.stressLevel != StressLevel.One) {
                        navigation.onNavigateToStressStressors()
                    } else {
                        stressLevelViewModel.onAction(
                            StressLevelIntent.UpdateAddingStressors(
                                Stressor.InPeace
                            )
                        )
                        stressLevelViewModel.onAction(StressLevelIntent.AddStressLevelRecord())
                    }
                }

                NextAndBackAction.OnGoBack -> navigation.popBackStack()
            }
        }
    )
}
