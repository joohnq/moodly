package com.joohnq.sleep_quality.data.driver

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import co.touchlab.sqliter.DatabaseConfiguration
import com.joohnq.sleep_quality.data.database.SleepQualityDatabase
import com.joohnq.sleep_quality.database.SleepQualityDatabaseSql

actual class SleepQualityDriverFactory {
    actual fun createDriver(): SqlDriver =
        NativeSqliteDriver(
            SleepQualityDatabaseSql.Schema,
            SleepQualityDatabase.DATABASE_NAME,
            onConfiguration = { config: DatabaseConfiguration ->
                config.copy(
                    extendedConfig = DatabaseConfiguration.Extended(foreignKeyConstraints = true)
                )
            }
        )
}