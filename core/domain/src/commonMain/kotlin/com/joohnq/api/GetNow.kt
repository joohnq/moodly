package com.joohnq.api

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun getNow(): LocalDateTime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
