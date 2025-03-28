package com.joohnq.domain

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun getNow(): LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())