package com.joohnq.sleep_quality.impl.data.database

import app.cash.sqldelight.db.SqlDriver
import com.joohnq.database.Database
import com.joohnq.sleep_quality.database.SleepQualityDatabaseSql

class SleepQualityDatabase(
    private val driver: SqlDriver,
) : Database<SleepQualityDatabaseSql>() {
    override operator fun invoke(): SleepQualityDatabaseSql = SleepQualityDatabaseSql(driver)

    override fun drop() {
        driver.execute(null, "DROP TABLE IF EXISTS SleepQualityRecord;", 0)
    }

    companion object {
        const val DATABASE_NAME = "sleep_quality.db"
    }
}
