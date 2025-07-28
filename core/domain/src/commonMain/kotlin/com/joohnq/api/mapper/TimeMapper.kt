package com.joohnq.api.mapper

import com.joohnq.api.entity.Time
import com.joohnq.api.mapper.IntMapper.toPaddedString

fun Time.toFormattedTimeString(): String = "${hour.toPaddedString()}:${minute.toPaddedString()}"

fun Time.toMinutes(): Int = hour * 60 + minute

fun Time.toHoursAndMinutesString(): String = "${hour}h ${minute}min"

fun calculateDuration(
    start: Time,
    end: Time,
): Time {
    val duration = end.toMinutes() - start.toMinutes()
    return duration.toTimeFromMinutes()
}
