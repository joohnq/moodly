package com.joohnq.moodapp.ui.presentation.add_sleep_quality

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.domain.Mood
import com.joohnq.moodapp.domain.SleepInfluences
import com.joohnq.moodapp.ui.components.AddSleepQualityTimePicker
import com.joohnq.moodapp.ui.components.ContinueButton
import com.joohnq.moodapp.ui.components.HorizontalSpacer
import com.joohnq.moodapp.ui.components.MoodFace
import com.joohnq.moodapp.ui.components.TextRadioButton
import com.joohnq.moodapp.ui.components.TimePickerCard
import com.joohnq.moodapp.ui.components.TimePickerDialog
import com.joohnq.moodapp.ui.components.Title
import com.joohnq.moodapp.ui.components.TopBar
import com.joohnq.moodapp.ui.components.VerticalSpacer
import com.joohnq.moodapp.ui.presentation.add_sleep_quality.event.AddSleepQualityEvent
import com.joohnq.moodapp.ui.presentation.add_sleep_quality.state.AddSleepQualityState
import com.joohnq.moodapp.ui.theme.Colors
import com.joohnq.moodapp.ui.theme.ComponentColors
import com.joohnq.moodapp.ui.theme.Dimens
import com.joohnq.moodapp.ui.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.moodapp.ui.theme.TextStyles
import com.joohnq.moodapp.util.helper.DatetimeManager
import com.joohnq.moodapp.viewmodel.SleepQualityIntent
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.end_sleeping_time
import moodapp.composeapp.generated.resources.mood
import moodapp.composeapp.generated.resources.new_sleep_quality
import moodapp.composeapp.generated.resources.sleeping_influences
import moodapp.composeapp.generated.resources.start_sleeping_time
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSleepQualityUI(
    state: AddSleepQualityState
) {
    val (snackBarState, showStartTimePickerDialog, showEndTimePickerDialog, startTimePickerState, endTimePickerState, selectedMood, selectedSleepInfluences, onEvent, onAction) = state
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
                TopBar(onGoBack = { onEvent(AddSleepQualityEvent.OnGoBack) })
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
                        onClick = { onAction(SleepQualityIntent.AddSleepQualityRecord()) }
                    )
                }
        }
    }
}