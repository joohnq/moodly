package com.joohnq.shared_resources.remember

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.joohnq.shared_resources.*
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.ui.entity.CalendarInfo

@Composable
fun rememberCalendarInfo(): List<CalendarInfo> =
    remember {
        listOf(
            CalendarInfo(
                borderColor = Colors.Gray30,
                backgroundColor = Colors.White,
                text = Res.string.skipped
            ),
            CalendarInfo(
                borderColor = Colors.Gray40,
                backgroundColor = Colors.Gray40,
                text = Res.string.neutral
            ),
            CalendarInfo(
                borderColor = Colors.Green40,
                backgroundColor = Colors.Green40,
                text = Res.string.positive
            ),
            CalendarInfo(
                borderColor = Colors.Pink40,
                backgroundColor = Colors.Pink40,
                text = Res.string.negative
            )
        )
    }
