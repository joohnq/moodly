package com.joohnq.user.data.driver

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.joohnq.user.data.database.UserPreferencesDatabase
import com.joohnq.user.database.user_preferences.UserPreferencesDatabaseSql

actual class UserPreferencesDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver =
        AndroidSqliteDriver(
            UserPreferencesDatabaseSql.Schema,
            context,
            UserPreferencesDatabase.DATABASE_NAME,
            callback = object : AndroidSqliteDriver.Callback(UserPreferencesDatabaseSql.Schema) {
                override fun onOpen(db: SupportSQLiteDatabase) {
                    db.setForeignKeyConstraintsEnabled(true)
                }
            }
        )
}