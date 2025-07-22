package com.joohnq.stress_level.data.driver

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import co.touchlab.sqliter.DatabaseConfiguration
import com.joohnq.stress_level.data.database.StressLevelDatabase
import com.joohnq.stress_level.database.StressLevelDatabaseSql

actual class StressLevelDriverFactory {
    actual fun createDriver(): SqlDriver =
        NativeSqliteDriver(
            StressLevelDatabaseSql.Schema,
            StressLevelDatabase.DATABASE_NAME,
            onConfiguration = { config: DatabaseConfiguration ->
                config.copy(
                    extendedConfig = DatabaseConfiguration.Extended(foreignKeyConstraints = true)
                )
            }
        )
}