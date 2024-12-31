package com.joohnq.user.data.driver

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.joohnq.user.data.database.UserDatabase
import com.joohnq.user.database.user.UserDatabaseSql

actual class UserDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver = AndroidSqliteDriver(
        UserDatabaseSql.Schema,
        context,
        UserDatabase.DATABASE_NAME,
        callback = object : AndroidSqliteDriver.Callback(UserDatabaseSql.Schema) {
            override fun onOpen(db: SupportSQLiteDatabase) {
                db.setForeignKeyConstraintsEnabled(true)
            }
        }
    )
}