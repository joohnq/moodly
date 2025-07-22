package com.joohnq.stress_level.impl.data.driver

import app.cash.sqldelight.db.SqlDriver

expect class StressLevelDriverFactory {
    fun createDriver(): SqlDriver
}