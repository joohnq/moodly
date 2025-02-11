package com.joohnq.domain.mapper

import com.joohnq.domain.entity.Time

fun Time.toFormattedTimeString(): String =
    "${hour.toPaddedString()}:${minute.toPaddedString()}"

fun Time.toMinutes(): Int = hour * 60 + minute

fun Time.toHoursAndMinutesString(): String =
    "${hour.toPaddedString()}h ${minute.toPaddedString()}min"

fun Pair<Time, Time>.calculateDuration(): Time {
    val duration = first.toMinutes() - second.toMinutes()
    return duration.toTimeFromMinutes()
}