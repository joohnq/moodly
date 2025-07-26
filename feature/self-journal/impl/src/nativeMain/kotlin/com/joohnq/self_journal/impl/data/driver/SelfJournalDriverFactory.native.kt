package com.joohnq.self_journal.impl.data.driver

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import co.touchlab.sqliter.DatabaseConfiguration
import com.joohnq.self_journal.database.SelfJournalDatabaseSql
import com.joohnq.self_journal.impl.data.database.SelfJournalDatabase

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
