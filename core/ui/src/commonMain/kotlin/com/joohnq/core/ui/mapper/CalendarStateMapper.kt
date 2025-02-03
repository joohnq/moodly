package com.joohnq.core.ui.mapper

import com.kizitonwose.calendar.compose.CalendarState
import com.kizitonwose.calendar.core.minusMonths
import com.kizitonwose.calendar.core.plusMonths
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun CoroutineScope.animateScrollToNextMonth(state: CalendarState) {
    launch {
        state.animateScrollToMonth(
            state.firstVisibleMonth.yearMonth.plusMonths(1)
        )
    }
}

fun CoroutineScope.animateScrollToPreviousMonth(state: CalendarState) {
    launch {
        state.animateScrollToMonth(
            state.firstVisibleMonth.yearMonth.minusMonths(1)
        )
    }
}