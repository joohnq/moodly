package com.joohnq.user.data.driver

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import co.touchlab.sqliter.DatabaseConfiguration
import com.joohnq.user.database.user.UserDatabaseSql

actual class UserDriverFactory {
    actual fun createDriver(): SqlDriver =
        NativeSqliteDriver(
            UserDatabaseSql.Schema,
            UserDatabase.DATABASE_NAME,
            onConfiguration = { config: DatabaseConfiguration ->
                config.copy(
                    extendedConfig = DatabaseConfiguration.Extended(foreignKeyConstraints = true)
                )
            }
        )
}