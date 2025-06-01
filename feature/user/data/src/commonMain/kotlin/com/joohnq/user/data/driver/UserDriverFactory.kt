package com.joohnq.user.data.driver

import app.cash.sqldelight.db.SqlDriver

expect class UserDriverFactory {
    fun createDriver(): SqlDriver
}