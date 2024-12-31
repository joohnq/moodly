package com.joohnq.user.data.driver

import app.cash.sqldelight.db.SqlDriver

expect class UserPreferencesDriverFactory {
    fun createDriver(): SqlDriver
}