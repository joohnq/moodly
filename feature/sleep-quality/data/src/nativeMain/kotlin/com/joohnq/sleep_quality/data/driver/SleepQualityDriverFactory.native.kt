package com.joohnq.sleep_quality.data.driver

import app.cash.sqldelight.db.SqlDriver

actual class SleepQualityDriverFactory() {
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