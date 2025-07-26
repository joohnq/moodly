package com.joohnq.sleep_quality.impl.data.driver

import app.cash.sqldelight.db.SqlDriver

expect class SleepQualityDriverFactory {
    fun createDriver(): SqlDriver
}
