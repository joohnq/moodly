package com.joohnq.sleep_quality.data.database

import com.joohnq.sleep_quality.data.driver.SleepQualityDriverFactory
import com.joohnq.sleep_quality.database.SleepQualityDatabaseSql

class SleepQualityDatabase(private val driverFactory: SleepQualityDriverFactory) {
    operator fun invoke(): SleepQualityDatabaseSql =
        SleepQualityDatabaseSql(driverFactory.createDriver())

    companion object {
        const val DATABASE_NAME = "sleep_quality.db"
    }
}