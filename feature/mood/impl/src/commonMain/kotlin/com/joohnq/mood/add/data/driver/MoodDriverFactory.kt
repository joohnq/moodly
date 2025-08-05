package com.joohnq.mood.add.data.driver

import app.cash.sqldelight.db.SqlDriver

expect class MoodDriverFactory {
    fun createDriver(): SqlDriver
}
