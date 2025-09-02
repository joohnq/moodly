package com.joohnq.database.driver

import app.cash.sqldelight.db.SqlDriver

expect class AppDatabaseDriverFactory {
    fun createDriver(): SqlDriver
}
