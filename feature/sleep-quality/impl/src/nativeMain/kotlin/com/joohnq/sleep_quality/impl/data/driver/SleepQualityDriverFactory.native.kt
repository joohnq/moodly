package com.joohnq.sleep_quality.impl.data.driver

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import co.touchlab.sqliter.DatabaseConfiguration
import com.joohnq.sleep_quality.database.SleepQualityDatabaseSql
import com.joohnq.sleep_quality.impl.data.database.SleepQualityDatabase

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
