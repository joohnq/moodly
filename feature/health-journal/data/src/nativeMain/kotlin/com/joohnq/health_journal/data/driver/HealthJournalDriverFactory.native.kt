package com.joohnq.health_journal.data.driver

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import co.touchlab.sqliter.DatabaseConfiguration
import com.joohnq.health_journal.data.database.HealthJournalDatabase
import com.joohnq.health_journal.database.HealthJournalDatabaseSql

actual class HealthJournalDriverFactory {
    actual fun createDriver(): SqlDriver =
        NativeSqliteDriver(
            HealthJournalDatabaseSql.Schema,
            HealthJournalDatabase.DATABASE_NAME,
            onConfiguration = { config: DatabaseConfiguration ->
                config.copy(
                    extendedConfig = DatabaseConfiguration.Extended(foreignKeyConstraints = true)
                )
            }
        )
}