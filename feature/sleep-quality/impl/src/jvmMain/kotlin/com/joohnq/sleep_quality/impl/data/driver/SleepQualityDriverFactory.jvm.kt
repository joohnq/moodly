package com.joohnq.sleep_quality.impl.data.driver

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.joohnq.sleep_quality.database.SleepQualityDatabaseSql
import com.joohnq.sleep_quality.impl.data.database.SleepQualityDatabase
import java.util.Properties

actual class SleepQualityDriverFactory {
    actual fun createDriver(): SqlDriver =
        JdbcSqliteDriver(
            url = "jdbc:sqlite:${SleepQualityDatabase.DATABASE_NAME}",
            properties = Properties().apply { put("foreign_keys", "true") },
            schema = SleepQualityDatabaseSql.Schema
        )
}
