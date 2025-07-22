package com.joohnq.api.mapper

import com.joohnq.api.entity.Minutes
import com.joohnq.api.entity.Time

fun Minutes.toTimeFromMinutes(): Time = Time(this / 60, this % 60)
