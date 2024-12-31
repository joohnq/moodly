package com.joohnq.mood.data.driver

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.joohnq.mood.data.database.StatsDatabase
import com.joohnq.mood.database.StatsDatabaseSql

actual class StatsDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver =
        AndroidSqliteDriver(
            StatsDatabaseSql.Schema,
            context,
            StatsDatabase.DATABASE_NAME,
            callback = object : AndroidSqliteDriver.Callback(StatsDatabaseSql.Schema) {
                override fun onOpen(db: SupportSQLiteDatabase) {
                    db.setForeignKeyConstraintsEnabled(true)
                }
            }
        )
}