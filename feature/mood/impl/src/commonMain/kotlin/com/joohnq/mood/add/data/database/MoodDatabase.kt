package com.joohnq.mood.add.data.database

import app.cash.sqldelight.db.SqlDriver
import com.joohnq.database.Database
import com.joohnq.mood.database.MoodDatabaseSql

class MoodDatabase(
    private val driver: SqlDriver,
) : Database<MoodDatabaseSql>() {
    override operator fun invoke(): MoodDatabaseSql = MoodDatabaseSql(driver)

    override fun drop() {
        driver.execute(null, "DROP TABLE IF EXISTS MoodRecord;", 0)
    }

    companion object {
        const val DATABASE_NAME = "mood.db"
    }
}
