package com.joohnq.stress_level.impl.data.driver

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.joohnq.stress_level.database.StressLevelDatabaseSql
import com.joohnq.stress_level.impl.data.database.StressLevelDatabase
import java.util.Properties

actual class StressLevelDriverFactory {
    actual fun createDriver(): SqlDriver =
        JdbcSqliteDriver(
            url = "jdbc:sqlite:${StressLevelDatabase.DATABASE_NAME}",
            properties = Properties().apply { put("foreign_keys", "true") },
            schema = StressLevelDatabaseSql.Schema
        )
}
