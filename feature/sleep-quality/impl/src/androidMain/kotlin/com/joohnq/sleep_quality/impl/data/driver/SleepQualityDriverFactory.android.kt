package com.joohnq.sleep_quality.impl.data.driver

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.joohnq.sleep_quality.database.SleepQualityDatabaseSql
import com.joohnq.sleep_quality.impl.data.database.SleepQualityDatabase

actual class SleepQualityDriverFactory(
    private val context: Context,
) {
    actual fun createDriver(): SqlDriver =
        AndroidSqliteDriver(
            SleepQualityDatabaseSql.Schema,
            context,
            SleepQualityDatabase.DATABASE_NAME,
            callback =
                object : AndroidSqliteDriver.Callback(SleepQualityDatabaseSql.Schema) {
                    override fun onOpen(db: SupportSQLiteDatabase) {
                        db.setForeignKeyConstraintsEnabled(true)
                    }
                }
        )
}
