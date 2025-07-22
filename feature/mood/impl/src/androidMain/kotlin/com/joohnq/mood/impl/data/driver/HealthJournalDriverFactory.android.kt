package com.joohnq.mood.impl.data.driver

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.joohnq.mood.impl.data.database.MoodDatabase
import com.joohnq.mood.database.MoodDatabaseSql

actual class MoodDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver =
        AndroidSqliteDriver(
            MoodDatabaseSql.Schema,
            context,
            MoodDatabase.DATABASE_NAME,
            callback = object : AndroidSqliteDriver.Callback(MoodDatabaseSql.Schema) {
                override fun onOpen(db: SupportSQLiteDatabase) {
                    db.setForeignKeyConstraintsEnabled(true)
                }
            }
        )
}