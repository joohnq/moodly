package com.joohnq.self_journal.impl.data.database

import app.cash.sqldelight.db.SqlDriver
import com.joohnq.database.Database
import com.joohnq.self_journal.database.SelfJournalDatabaseSql

class SelfJournalDatabase(
    private val driverFactory: SqlDriver,
) : Database<SelfJournalDatabaseSql>() {
    override operator fun invoke(): SelfJournalDatabaseSql = SelfJournalDatabaseSql(driverFactory)

    companion object {
        const val DATABASE_NAME = "self_journal"
    }
}
