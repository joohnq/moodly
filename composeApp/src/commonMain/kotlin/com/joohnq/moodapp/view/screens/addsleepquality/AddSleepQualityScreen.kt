package com.joohnq.moodapp.view.screens.addsleepquality

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.joohnq.moodapp.entities.Mood
import com.joohnq.moodapp.entities.SleepInfluences
import com.joohnq.moodapp.helper.DatetimeManager
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.view.NextAndBackAction
import com.joohnq.moodapp.view.components.AddSleepQualityTimePicker
import com.joohnq.moodapp.view.components.ContinueButton
import com.joohnq.moodapp.view.components.HorizontalSpacer
import com.joohnq.moodapp.view.components.MoodFace
import com.joohnq.moodapp.view.components.TextRadioButton
import com.joohnq.moodapp.view.components.TimePickerCard
import com.joohnq.moodapp.view.components.TimePickerDialog
import com.joohnq.moodapp.view.components.Title
import com.joohnq.moodapp.view.components.TopBar
import com.joohnq.moodapp.view.components.VerticalSpacer
import com.joohnq.moodapp.view.routes.onNavigateToHomeGraph
import com.joohnq.moodapp.view.state.UiState.Companion.fold
import com.joohnq.moodapp.view.ui.Colors
import com.joohnq.moodapp.view.ui.ComponentColors
import com.joohnq.moodapp.view.ui.Dimens
import com.joohnq.moodapp.view.ui.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.moodapp.view.ui.TextStyles
import com.joohnq.moodapp.viewmodel.SleepQualityIntent
import com.joohnq.moodapp.viewmodel.SleepQualityViewModel
import kotlinx.coroutines.launch
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.end_sleeping_time
import moodapp.composeapp.generated.resources.mood
import moodapp.composeapp.generated.resources.new_sleep_quality
import moodapp.composeapp.generated.resources.sleeping_influences
import moodapp.composeapp.generated.resources.start_sleeping_time
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSleepQualityUi(
    snackBarState: SnackbarHostState = remember { SnackbarHostState() },
    showStartTimePickerDialog: Boolean,
    showEndTimePickerDialog: Boolean,
    startTimePickerState: TimePickerState,
    endTimePickerState: TimePickerState,
    selectedMood: Mood?,
    selectedSleepInfluences: List<SleepInfluences>,
    onNavigation: (NextAndBackAction) -> Unit = {},
    onAction: (SleepQualityIntent) -> Unit = {}
) {
    val moods = remember { Mood.getAll() }
    val sleepInfluences = remember { SleepInfluences.getAll() }

    if (showStartTimePickerDialog)
        TimePickerDialog(
            title = Res.string.start_sleeping_time,
            onDismiss = { onAction(SleepQualityIntent.UpdateShowStartTimePickerDialog(false)) },
            onConfirm = {
                onAction(SleepQualityIntent.UpdateShowStartTimePickerDialog(false))
                onAction(
                    SleepQualityIntent.UpdateStartTime(
                        startTimePickerState.hour,
                        startTimePickerState.minute
                    )
                )
            },
        ) {
            AddSleepQualityTimePicker(startTimePickerState)
        }

    if (showEndTimePickerDialog)
        TimePickerDialog(
            title = Res.string.end_sleeping_time,
            onDismiss = { onAction(SleepQualityIntent.UpdateShowEndTimePickerDialog(false)) },
            onConfirm = {
                onAction(SleepQualityIntent.UpdateShowEndTimePickerDialog(false))
                onAction(
                    SleepQualityIntent.UpdateEndTime(
                        endTimePickerState.hour,
                        endTimePickerState.minute
                    )
                )
            },
        ) {
            AddSleepQualityTimePicker(endTimePickerState)
        }

    Scaffold(
        containerColor = Colors.Brown10,
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackBarState) }
    ) { padding ->
        Column(
            Modifier.fillMaxSize()
                .padding(padding),
        ) {
            Box(modifier = Modifier.paddingHorizontalMedium()) {
                TopBar(onGoBack = { onNavigation(NextAndBackAction.OnGoBack) })
            }
            VerticalSpacer(40.dp)
            Text(
                text = stringResource(Res.string.new_sleep_quality),
                style = TextStyles.HeadingSmExtraBold(),
                color = Colors.Brown80,
                textAlign = TextAlign.Center,
                modifier = Modifier.paddingHorizontalMedium()
            )
            VerticalSpacer(32.dp)
            Row(
                modifier = Modifier.fillMaxWidth().paddingHorizontalMedium(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TimePickerCard(
                    modifier = Modifier.weight(1f),
                    title = Res.string.start_sleeping_time,
                    hour = DatetimeManager.formatInt(startTimePickerState.hour),
                    minutes = DatetimeManager.formatInt(startTimePickerState.minute),
                    isAfternoon = startTimePickerState.isAfternoon,
                    onClick = { onAction(SleepQualityIntent.UpdateShowStartTimePickerDialog(true)) }
                )
                HorizontalSpacer(20.dp)
                TimePickerCard(
                    modifier = Modifier.weight(1f),
                    title = Res.string.end_sleeping_time,
                    hour = DatetimeManager.formatInt(endTimePickerState.hour),
                    minutes = DatetimeManager.formatInt(endTimePickerState.minute),
                    isAfternoon = endTimePickerState.isAfternoon,
                    onClick = { onAction(SleepQualityIntent.UpdateShowEndTimePickerDialog(true)) }
                )
            }
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Title(text = Res.string.mood)
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(horizontal = 20.dp)
                ) {
                    items(moods) {
                        MoodFace(
                            modifier = Modifier.size(32.dp),
                            mood = it,
                            backgroundColor = if (selectedMood == it) it.palette.faceBackgroundColor else Colors.Gray30,
                            color = if (selectedMood == it) it.palette.faceColor else Colors.Gray60,
                            onClick = { onAction(SleepQualityIntent.UpdateAddingMood(it)) }
                        )
                    }
                }
            }
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Title(text = Res.string.sleeping_influences)
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(horizontal = 20.dp)
                ) {
                    items(sleepInfluences) {
                        TextRadioButton(
                            text = stringResource(it.title),
                            selected = selectedSleepInfluences.contains(it),
                            colors = ComponentColors.RadioButton.TextRadioButtonColors(),
                            shape = Dimens.Shape.Circle,
                            onClick = { onAction(SleepQualityIntent.UpdateSelectedSleepInfluence(it)) }
                        )
                    }
                }
            }
            VerticalSpacer(48.dp)
            if (selectedMood != null)
                Box(modifier = Modifier.paddingHorizontalMedium()) {
                    ContinueButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { onNavigation(NextAndBackAction.OnContinue) }
                    )
                }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSleepQualityScreen(
    navigation: NavHostController,
    sleepQualityViewModel: SleepQualityViewModel = sharedViewModel()
) {
    val scope = rememberCoroutineScope()
    val snackBarState = remember { SnackbarHostState() }
    val sleepQualityState by sleepQualityViewModel.sleepQualityState.collectAsState()
    val startTimePickerState = rememberTimePickerState(
        initialHour = sleepQualityState.adding.startHour,
        initialMinute = sleepQualityState.adding.startMinute,
        is24Hour = true,
    )
    val endTimePickerState = rememberTimePickerState(
        initialHour = sleepQualityState.adding.endHour,
        initialMinute = sleepQualityState.adding.endMinute,
        is24Hour = true,
    )

    LaunchedEffect(sleepQualityState.adding.status) {
        sleepQualityState.adding.status.fold(
            onError = { error -> scope.launch { snackBarState.showSnackbar(error) } },
            onSuccess = {
                navigation.onNavigateToHomeGraph()
                sleepQualityViewModel.onAction(SleepQualityIntent.ResetAdding)
            },
        )
    }

    AddSleepQualityUi(
        snackBarState = snackBarState,
        showStartTimePickerDialog = sleepQualityState.adding.showStartTimePickerDialog,
        showEndTimePickerDialog = sleepQualityState.adding.showEndTimePickerDialog,
        startTimePickerState = startTimePickerState,
        endTimePickerState = endTimePickerState,
        selectedMood = sleepQualityState.adding.mood,
        selectedSleepInfluences = sleepQualityState.adding.selectedSleepInfluences,
        onAction = sleepQualityViewModel::onAction,
        onNavigation = { action ->
            when (action) {
                NextAndBackAction.OnGoBack -> navigation.popBackStack()
                NextAndBackAction.OnContinue -> sleepQualityViewModel.onAction(SleepQualityIntent.AddSleepQualityRecord())
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AddSleepQualityPreview() {
    AddSleepQualityUi(
        showStartTimePickerDialog = false,
        showEndTimePickerDialog = false,
        selectedMood = Mood.Neutral,
        selectedSleepInfluences = mutableListOf(SleepInfluences.ChillSleepEnvironment),
        startTimePickerState = rememberTimePickerState(
            initialHour = 0,
            initialMinute = 0,
            is24Hour = true,
        ),
        endTimePickerState = rememberTimePickerState(
            initialHour = 0,
            initialMinute = 0,
            is24Hour = true,
        ),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AddSleepQualityPreview2() {
    AddSleepQualityUi(
        showStartTimePickerDialog = true,
        showEndTimePickerDialog = false,
        selectedMood = Mood.Neutral,
        selectedSleepInfluences = mutableListOf(SleepInfluences.ChillSleepEnvironment),
        startTimePickerState = rememberTimePickerState(
            initialHour = 0,
            initialMinute = 0,
            is24Hour = true,
        ),
        endTimePickerState = rememberTimePickerState(
            initialHour = 0,
            initialMinute = 0,
            is24Hour = true,
        ),
    )
}

