package com.joohnq.sleep_quality.domain.exception

sealed class SleepQualityException : Throwable(message = "Sleep Quality Exception") {
    data object AlreadyBeenAddedToday : SleepQualityException()
}