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
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joohnq.moodapp.entities.StressLevel
import com.joohnq.moodapp.entities.Stressors
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.view.components.ContinueButton
import com.joohnq.moodapp.view.components.SleepQualityThumb
import com.joohnq.moodapp.view.components.SleepQualityTrack
import com.joohnq.moodapp.view.components.TopBar
import com.joohnq.moodapp.view.components.VerticalSlider
import com.joohnq.moodapp.view.components.VerticalSpacer
import com.joohnq.moodapp.view.routes.onNavigateToHomeGraph
import com.joohnq.moodapp.view.routes.onNavigateToStressStressors
import com.joohnq.moodapp.view.state.UiState.Companion.fold
import com.joohnq.moodapp.view.ui.Colors
import com.joohnq.moodapp.view.ui.ComponentColors
import com.joohnq.moodapp.view.ui.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.moodapp.view.ui.PaddingModifier.Companion.paddingHorizontalSmall
import com.joohnq.moodapp.view.ui.TextStyles
import com.joohnq.moodapp.viewmodel.StressLevelViewModel
import kotlinx.coroutines.launch
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.add_stress_level
import moodapp.composeapp.generated.resources.whats_your_stress_level_today
import org.jetbrains.compose.resources.stringResource

data class ValueSetValue<T>(
    val value: T,
    val setValue: (T) -> Unit
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddStressLevelScreenUI(
    snackBarState: SnackbarHostState = remember { SnackbarHostState() },
    sliderValue: Float,
    selectedStressLevel: StressLevel,
    setSliderValue: (Float) -> Unit = {},
    onGoBack: () -> Unit = {},
    onContinue: () -> Unit = {},
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
                TopBar(text = Res.string.add_stress_level, onGoBack = onGoBack)
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
                        setSliderValue = setSliderValue,
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
                    onClick = onContinue
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
    var sliderValue by rememberSaveable { mutableFloatStateOf(0f) }
    val setSliderValue: (Float) -> Unit = remember {
        {
            sliderValue = it
            stressLevelViewModel.updateAddingStressLevel(StressLevel.fromSliderValue(sliderValue))
        }
    }
    val onContinue: () -> Unit = remember {
        {
            if (stressLevelState.addingStressLevel != StressLevel.One) {
                navigation.onNavigateToStressStressors()
            } else {
                stressLevelViewModel.updateAddingStressStressors(Stressors.InPeace)
                stressLevelViewModel.addStressLevelRecord()
            }
        }
    }

    LaunchedEffect(stressLevelState.addingStatus) {
        stressLevelState.addingStatus.fold(
            onError = { error -> scope.launch { snackBarState.showSnackbar(error) } },
            onSuccess = {
                navigation.onNavigateToHomeGraph()
                stressLevelViewModel.resetAddingStressLevel()
            },
        )
    }

    AddStressLevelScreenUI(
        snackBarState = snackBarState,
        onContinue = onContinue,
        sliderValue = sliderValue,
        setSliderValue = setSliderValue,
        selectedStressLevel = stressLevelState.addingStressLevel,
        onGoBack = navigation::popBackStack
    )
}
