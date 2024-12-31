package com.joohnq.mood.data.driver

import app.cash.sqldelight.db.SqlDriver

expect class StatsDriverFactory {
    fun createDriver(): SqlDriver
}