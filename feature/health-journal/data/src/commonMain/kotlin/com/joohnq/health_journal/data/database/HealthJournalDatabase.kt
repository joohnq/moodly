package com.joohnq.health_journal.data.database

import app.cash.sqldelight.db.SqlDriver
import com.joohnq.core.database.Database
import com.joohnq.health_journal.database.HealthJournalDatabaseSql

class HealthJournalDatabase(private val driverFactory: SqlDriver) :
    Database<HealthJournalDatabaseSql>() {
    override operator fun invoke(): HealthJournalDatabaseSql =
        HealthJournalDatabaseSql(driverFactory)

    companion object {
        const val DATABASE_NAME = "health_journal"
    }
}