package com.joohnq.sleep_quality.api.exception

sealed class SleepQualityException(override val message: String) : Exception(message) {
    data object AlreadyBeenAddedToday :
        SleepQualityException("A sleep quality record has already been added for today.")
}