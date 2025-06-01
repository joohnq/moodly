package com.joohnq.stress_level.data.driver

import app.cash.sqldelight.db.SqlDriver

expect class StressLevelDriverFactory {
    fun createDriver(): SqlDriver
}