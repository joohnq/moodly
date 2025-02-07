package com.joohnq.self_journal.data.driver

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import co.touchlab.sqliter.DatabaseConfiguration
import com.joohnq.self_journal.data.database.SelfJournalDatabase
import com.joohnq.self_journal.database.SelfJournalDatabaseSql

actual class SelfJournalDriverFactory {
    actual fun createDriver(): SqlDriver =
        NativeSqliteDriver(
            SelfJournalDatabaseSql.Schema,
            SelfJournalDatabase.DATABASE_NAME,
            onConfiguration = { config: DatabaseConfiguration ->
                config.copy(
                    extendedConfig = DatabaseConfiguration.Extended(foreignKeyConstraints = true)
                )
            }
        )
}