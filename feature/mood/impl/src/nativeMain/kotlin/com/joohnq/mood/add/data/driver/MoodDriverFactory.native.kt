package com.joohnq.mood.add.data.driver

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import co.touchlab.sqliter.DatabaseConfiguration
import com.joohnq.mood.add.data.database.MoodDatabase
import com.joohnq.mood.database.MoodDatabaseSql

actual class MoodDriverFactory {
    actual fun createDriver(): SqlDriver =
        NativeSqliteDriver(
            MoodDatabaseSql.Schema,
            MoodDatabase.DATABASE_NAME,
            onConfiguration = { config: DatabaseConfiguration ->
                config.copy(
                    extendedConfig = DatabaseConfiguration.Extended(foreignKeyConstraints = true)
                )
            }
        )
}
