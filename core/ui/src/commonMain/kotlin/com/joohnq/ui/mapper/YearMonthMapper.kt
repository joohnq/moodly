package com.joohnq.ui.mapper

import com.kizitonwose.calendar.core.YearMonth

fun YearMonth.toMonthAndYearCompleteString(): String =
    "${month.name} $year"