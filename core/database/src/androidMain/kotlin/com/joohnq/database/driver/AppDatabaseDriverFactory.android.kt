package com.joohnq.database.driver

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.joohnq.database.AppDatabaseSql
import com.joohnq.database.AppSqlDelightDatabase

actual class AppDatabaseDriverFactory(
    private val context: Context,
) {
    actual fun createDriver(): SqlDriver =
        AndroidSqliteDriver(
            AppDatabaseSql.Schema,
            context,
            AppSqlDelightDatabase.DATABASE_NAME,
            callback =
                object : AndroidSqliteDriver.Callback(AppDatabaseSql.Schema) {
                    override fun onOpen(db: SupportSQLiteDatabase) {
                        db.setForeignKeyConstraintsEnabled(true)
                    }
                }
        )
}
