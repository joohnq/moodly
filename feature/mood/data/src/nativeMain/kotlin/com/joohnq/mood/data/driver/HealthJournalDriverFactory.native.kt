package com.joohnq.mood.data.driver

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import co.touchlab.sqliter.DatabaseConfiguration
import com.joohnq.mood.data.database.StatsDatabase
import com.joohnq.mood.database.StatsDatabaseSql

actual class StatsDriverFactory {
    actual fun createDriver(): SqlDriver =
        NativeSqliteDriver(
            StatsDatabaseSql.Schema,
            StatsDatabase.DATABASE_NAME,
            onConfiguration = { config: DatabaseConfiguration ->
                config.copy(
                    extendedConfig = DatabaseConfiguration.Extended(foreignKeyConstraints = true)
                )
            }
        )
}