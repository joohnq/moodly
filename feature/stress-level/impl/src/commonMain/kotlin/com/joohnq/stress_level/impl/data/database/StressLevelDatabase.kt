package com.joohnq.stress_level.impl.data.database

import app.cash.sqldelight.db.SqlDriver
import com.joohnq.database.Database
import com.joohnq.stress_level.database.StressLevelDatabaseSql

class StressLevelDatabase(
    private val driver: SqlDriver,
) : Database<StressLevelDatabaseSql>() {
    override operator fun invoke(): StressLevelDatabaseSql = StressLevelDatabaseSql(driver)

    companion object {
        const val DATABASE_NAME = "stress_level.db"
    }
}
