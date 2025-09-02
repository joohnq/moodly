package com.joohnq.self_journal.impl.data.database

import app.cash.sqldelight.db.SqlDriver
import com.joohnq.database.Database
import com.joohnq.self_journal.database.SelfJournalDatabaseSql

class SelfJournalDatabase(
    private val driver: SqlDriver,
) : Database<SelfJournalDatabaseSql>() {
    override operator fun invoke(): SelfJournalDatabaseSql = SelfJournalDatabaseSql(driver)

    override fun drop() {
        driver.execute(null, "DROP TABLE IF EXISTS SelfJournalRecord;", 0)
    }

    companion object {
        const val DATABASE_NAME = "self_journal"
    }
}
