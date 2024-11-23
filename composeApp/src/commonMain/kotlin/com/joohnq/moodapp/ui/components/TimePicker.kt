package com.joohnq.moodapp.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerColors
import androidx.compose.material3.TimePickerState
import androidx.compose.runtime.Composable
import com.joohnq.moodapp.ui.theme.Colors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSleepQualityTimePicker(
    timePickerState: TimePickerState
) {
    TimePicker(
        state = timePickerState,
        colors = TimePickerColors(
            clockDialColor = Colors.White,
            selectorColor = Colors.Brown80,
            containerColor = Colors.White,
            periodSelectorBorderColor = Colors.Brown80,
            clockDialSelectedContentColor = Colors.White,
            clockDialUnselectedContentColor = Colors.Brown80,
            periodSelectorSelectedContainerColor = Colors.White,
            periodSelectorUnselectedContainerColor = Colors.White,
            periodSelectorSelectedContentColor = Colors.Brown80,
            periodSelectorUnselectedContentColor = Colors.Brown80,
            timeSelectorSelectedContainerColor = Colors.White,
            timeSelectorUnselectedContainerColor = Colors.White,
            timeSelectorSelectedContentColor = Colors.Brown80,
            timeSelectorUnselectedContentColor = Colors.Brown80
        ),
    )
}