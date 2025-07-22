package com.joohnq.stress_level.impl.data.driver

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.joohnq.stress_level.impl.data.database.StressLevelDatabase
import com.joohnq.stress_level.database.StressLevelDatabaseSql

actual class StressLevelDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver =
        AndroidSqliteDriver(
            StressLevelDatabaseSql.Schema,
            context,
            StressLevelDatabase.DATABASE_NAME,
            callback = object : AndroidSqliteDriver.Callback(StressLevelDatabaseSql.Schema) {
                override fun onOpen(db: SupportSQLiteDatabase) {
                    db.setForeignKeyConstraintsEnabled(true)
                }
            }
        )
}