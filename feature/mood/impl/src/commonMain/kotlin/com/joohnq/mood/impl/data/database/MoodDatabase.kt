package com.joohnq.mood.impl.data.database

import app.cash.sqldelight.db.SqlDriver
import com.joohnq.database.Database
import com.joohnq.mood.database.MoodDatabaseSql

class MoodDatabase(private val driver: SqlDriver) : Database<MoodDatabaseSql>() {
    override operator fun invoke(): MoodDatabaseSql =
        MoodDatabaseSql(driver)

    companion object {
        const val DATABASE_NAME = "mood.db"
    }
}