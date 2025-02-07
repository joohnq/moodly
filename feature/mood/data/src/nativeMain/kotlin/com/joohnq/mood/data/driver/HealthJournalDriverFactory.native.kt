package com.joohnq.mood.data.driver

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import co.touchlab.sqliter.DatabaseConfiguration
import com.joohnq.mood.data.database.MoodDatabase
import com.joohnq.mood.database.StatsDatabaseSql

actual class MoodDriverFactory {
    actual fun createDriver(): SqlDriver =
        NativeSqliteDriver(
            StatsDatabaseSql.Schema,
            MoodDatabase.DATABASE_NAME,
            onConfiguration = { config: DatabaseConfiguration ->
                config.copy(
                    extendedConfig = DatabaseConfiguration.Extended(foreignKeyConstraints = true)
                )
            }
        )
}