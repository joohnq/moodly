package com.joohnq.api.mapper

import com.joohnq.api.entity.Year

fun Year.isYearLeap(): Boolean =
    (this % 4 == 0 && this % 100 != 0) || (this % 400 == 0)

fun Year.getTotalDays(): Int =
    if (isYearLeap()) 366 else 365