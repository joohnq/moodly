package com.joohnq.user.impl.data.driver

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.joohnq.user.database.UserDatabaseSql
import com.joohnq.user.impl.data.database.UserDatabase
import java.util.Properties

actual class UserDriverFactory {
    actual fun createDriver(): SqlDriver =
        JdbcSqliteDriver(
            url = "jdbc:sqlite:${UserDatabase.DATABASE_NAME}",
            properties = Properties().apply { put("foreign_keys", "true") },
            schema = UserDatabaseSql.Schema
        )
}
