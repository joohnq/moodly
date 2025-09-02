package com.joohnq.database.driver

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.joohnq.database.AppDatabaseSql
import com.joohnq.database.AppSqlDelightDatabase
import java.util.Properties

actual class AppDatabaseDriverFactory {
    actual fun createDriver(): SqlDriver =
        JdbcSqliteDriver(
            url = "jdbc:sqlite:${AppSqlDelightDatabase.DATABASE_NAME}",
            properties = Properties().apply { put("foreign_keys", "true") },
            schema = AppDatabaseSql.Schema
        )
}
