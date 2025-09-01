package com.joohnq.mood.add.data.driver

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.joohnq.mood.add.data.database.MoodDatabase
import com.joohnq.mood.database.MoodDatabaseSql
import java.util.Properties

actual class MoodDriverFactory {
    actual fun createDriver(): SqlDriver =
        JdbcSqliteDriver(
            url = "jdbc:sqlite:${MoodDatabase.DATABASE_NAME}",
            properties = Properties().apply { put("foreign_keys", "true") },
            schema = MoodDatabaseSql.Schema
        )
}
