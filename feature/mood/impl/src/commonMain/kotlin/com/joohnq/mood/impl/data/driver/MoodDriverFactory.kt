package com.joohnq.mood.impl.data.driver

import app.cash.sqldelight.db.SqlDriver

expect class MoodDriverFactory {
    fun createDriver(): SqlDriver
}
