package com.joohnq.core.ui.mapper

import com.joohnq.core.ui.entity.Minutes
import com.joohnq.core.ui.entity.Time

fun Minutes.toTimeFromMinutes(): Time = Time(this / 60, this % 60)
