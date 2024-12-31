package com.joohnq.health_journal.data.driver

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.joohnq.health_journal.data.database.HealthJournalDatabase
import com.joohnq.health_journal.database.HealthJournalDatabaseSql

actual class HealthJournalDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver =
        AndroidSqliteDriver(
            HealthJournalDatabaseSql.Schema,
            context,
            HealthJournalDatabase.DATABASE_NAME,
            callback = object : AndroidSqliteDriver.Callback(HealthJournalDatabaseSql.Schema) {
                override fun onOpen(db: SupportSQLiteDatabase) {
                    db.setForeignKeyConstraintsEnabled(true)
                }
            }
        )
}