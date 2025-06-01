package com.joohnq.sleep_quality.ui.presentation.add_sleep_quality

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
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.domain.mapper.toPaddedString
import com.joohnq.mood.ui.components.MoodFace
import com.joohnq.mood.ui.resource.mapper.getAllMoodResource
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.AddSleepQualityTimePicker
import com.joohnq.shared_resources.components.ContinueButton
import com.joohnq.shared_resources.components.HorizontalSpacer
import com.joohnq.shared_resources.components.ScaffoldSnackBar
import com.joohnq.shared_resources.components.TextRadioButton
import com.joohnq.shared_resources.components.TimePickerCard
import com.joohnq.shared_resources.components.TimePickerDialog
import com.joohnq.shared_resources.components.Title
import com.joohnq.shared_resources.components.TopBar
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.end_sleeping_time
import com.joohnq.shared_resources.mood
import com.joohnq.shared_resources.new_sleep_quality
import com.joohnq.shared_resources.sleeping_influences
import com.joohnq.shared_resources.start_sleeping_time
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.ComponentColors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.sleep_quality.ui.resource.mapper.getAllSleepInfluencesResource
import com.joohnq.sleep_quality.ui.resource.mapper.toMoodResource
import com.joohnq.sleep_quality.ui.presentation.add_sleep_quality.viewmodel.AddSleepQualityContract
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSleepQualityUI(
    snackBarState: SnackbarHostState,
    state: AddSleepQualityContract.State,
    onEvent: (AddSleepQualityContract.Event) -> Unit = {},
    onIntent: (AddSleepQualityContract.Intent) -> Unit = {},
) {
    val moods = remember { getAllMoodResource() }
    val sleepInfluences = remember { getAllSleepInfluencesResource() }
    val startTimePickerState = rememberTimePickerState(
        initialHour = state.record.startSleeping.hour,
        initialMinute = state.record.startSleeping.minute,
        is24Hour = true,
    )
    val endTimePickerState = rememberTimePickerState(
        initialHour = state.record.endSleeping.hour,
        initialMinute = state.record.endSleeping.minute,
        is24Hour = true,
    )

    if (state.showStartTimePickerDialog) {
        TimePickerDialog(
            title = Res.string.start_sleeping_time,
            onDismiss = {
                onIntent(
                    AddSleepQualityContract.Intent.UpdateShowStartTimePickerDialog(
                        false
                    )
                )
            },
            onConfirm = {
                onIntent(AddSleepQualityContract.Intent.UpdateShowStartTimePickerDialog(false))
                onIntent(
                    AddSleepQualityContract.Intent.UpdateStartTime(
                        startTimePickerState.hour,
                        startTimePickerState.minute
                    )
                )
            },
        ) {
            AddSleepQualityTimePicker(startTimePickerState)
        }
    }

    if (state.showEndTimePickerDialog) {
        TimePickerDialog(
            title = Res.string.end_sleeping_time,
            onDismiss = {
                onIntent(AddSleepQualityContract.Intent.UpdateShowStartTimePickerDialog(false))
            },
            onConfirm = {
                onIntent(AddSleepQualityContract.Intent.UpdateShowEndTimePickerDialog(false))
                onIntent(
                    AddSleepQualityContract.Intent.UpdateEndTime(
                        endTimePickerState.hour,
                        endTimePickerState.minute
                    )
                )
            },
        ) {
            AddSleepQualityTimePicker(endTimePickerState)
        }
    }

    ScaffoldSnackBar(
        containerColor = Colors.Brown10,
        modifier = Modifier.fillMaxSize(),
        snackBarHostState = snackBarState
    ) { padding ->
        Column(
            Modifier.fillMaxSize()
                .padding(padding),
        ) {
            Box(modifier = Modifier.paddingHorizontalMedium()) {
                TopBar(onGoBack = { onEvent(AddSleepQualityContract.Event.GoBack) })
            }
            VerticalSpacer(40.dp)
            Text(
                text = stringResource(Res.string.new_sleep_quality),
                style = TextStyles.headingSmExtraBold(),
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
                    hour = startTimePickerState.hour.toPaddedString(),
                    minutes = startTimePickerState.minute.toPaddedString(),
                    isAfternoon = startTimePickerState.isAfternoon,
                    onClick = {
                        onIntent(
                            AddSleepQualityContract.Intent.UpdateShowStartTimePickerDialog(true)
                        )
                    }
                )
                HorizontalSpacer(20.dp)
                TimePickerCard(
                    modifier = Modifier.weight(1f),
                    title = Res.string.end_sleeping_time,
                    hour = endTimePickerState.hour.toPaddedString(),
                    minutes = endTimePickerState.minute.toPaddedString(),
                    isAfternoon = endTimePickerState.isAfternoon,
                    onClick = {
                        onIntent(
                            AddSleepQualityContract.Intent.UpdateShowEndTimePickerDialog(
                                true
                            )
                        )
                    }
                )
            }
            VerticalSpacer(20.dp)
            Title(
                modifier = Modifier.paddingHorizontalMedium(),
                text = Res.string.mood
            )
            VerticalSpacer(10.dp)
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(horizontal = 20.dp)
            ) {
                items(moods) { resource ->
                    val moodResource = state.record.sleepQuality.toMoodResource()
                    MoodFace(
                        modifier = Modifier.size(32.dp),
                        resource = resource,
                        backgroundColor = if (moodResource == resource) resource.palette.faceBackgroundColor else Colors.Gray30,
                        color = if (moodResource == resource) resource.palette.faceColor else Colors.Gray60,
                        onClick = { onIntent(AddSleepQualityContract.Intent.UpdateMood(resource)) }
                    )
                }
            }
            VerticalSpacer(20.dp)
            Title(
                modifier = Modifier.paddingHorizontalMedium(),
                text = Res.string.sleeping_influences
            )
            VerticalSpacer(10.dp)
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(horizontal = 20.dp)
            ) {
                items(sleepInfluences) { sleepInfluences ->
                    TextRadioButton(
                        text = sleepInfluences.title,
                        selected = state.record.sleepInfluences.contains(
                            sleepInfluences
                        ),
                        colors = ComponentColors.RadioButton.TextRadioButtonColors(),
                        shape = Dimens.Shape.Circle,
                        onClick = {
                            onIntent(
                                AddSleepQualityContract.Intent.UpdateSelectedSleepInfluence(
                                    sleepInfluences
                                )
                            )
                        }
                    )
                }
            }
            VerticalSpacer(48.dp)
            ContinueButton(
                modifier = Modifier.paddingHorizontalMedium().fillMaxWidth(),
                onClick = { onEvent(AddSleepQualityContract.Event.Add) }
            )
        }
    }
}