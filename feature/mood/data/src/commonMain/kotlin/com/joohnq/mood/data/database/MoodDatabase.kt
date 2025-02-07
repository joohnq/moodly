package com.joohnq.mood.data.database

import app.cash.sqldelight.db.SqlDriver
import com.joohnq.core.database.Database
import com.joohnq.mood.database.StatsDatabaseSql

class MoodDatabase(private val driver: SqlDriver) : Database<StatsDatabaseSql>() {
    override operator fun invoke(): StatsDatabaseSql =
        StatsDatabaseSql(driver)

    companion object {
        const val DATABASE_NAME = "mood.db"
    }
}