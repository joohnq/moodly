package com.joohnq.self_journal.impl.data.driver

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.joohnq.self_journal.database.SelfJournalDatabaseSql
import com.joohnq.self_journal.impl.data.database.SelfJournalDatabase

actual class SelfJournalDriverFactory(
    private val context: Context,
) {
    actual fun createDriver(): SqlDriver =
        AndroidSqliteDriver(
            SelfJournalDatabaseSql.Schema,
            context,
            SelfJournalDatabase.DATABASE_NAME,
            callback =
                object : AndroidSqliteDriver.Callback(SelfJournalDatabaseSql.Schema) {
                    override fun onOpen(db: SupportSQLiteDatabase) {
                        db.setForeignKeyConstraintsEnabled(true)
                    }
                }
        )
}
