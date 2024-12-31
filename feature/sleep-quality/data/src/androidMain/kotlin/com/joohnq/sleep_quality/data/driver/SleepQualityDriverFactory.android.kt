package com.joohnq.sleep_quality.data.driver

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.joohnq.sleep_quality.data.database.SleepQualityDatabase

actual class SleepQualityDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver =
        AndroidSqliteDriver(
            StatsDatabaseSql.Schema,
            context,
            SleepQualityDatabase.DATABASE_NAME,
            callback = object : AndroidSqliteDriver.Callback(StatsDatabaseSql.Schema) {
                override fun onOpen(db: SupportSQLiteDatabase) {
                    db.setForeignKeyConstraintsEnabled(true)
                }
            }
        )
}