package com.joohnq.core.ui.mapper

import com.kizitonwose.calendar.core.YearMonth

fun YearMonth.toMonthAndYearCompleteString(): String =
    "${month.name} $year"