package com.joohnq.mood.domain

sealed class StatsException(override val message: String) : Exception(message) {
    data object StatsAlreadyAdded : StatsException("Today an stat has already been added")
}