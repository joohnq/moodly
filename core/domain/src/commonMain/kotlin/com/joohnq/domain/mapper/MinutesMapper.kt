package com.joohnq.domain.mapper

import com.joohnq.domain.entity.Minutes
import com.joohnq.domain.entity.Time

fun Minutes.toTimeFromMinutes(): Time = Time(this / 60, this % 60)
