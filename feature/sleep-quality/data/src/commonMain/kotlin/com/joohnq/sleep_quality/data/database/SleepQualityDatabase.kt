package com.joohnq.sleep_quality.data.database

import com.joohnq.sleep_quality.data.driver.SleepQualityDriverFactory

class SleepQualityDatabase(private val sleepQualityDriverFactory: SleepQualityDriverFactory) {
    operator fun invoke(): SleepQualityDatabaseSql =
        SleepQualityDatabaseSql(sleepQualityDriverFactory.createDriver())

    companion object {
        const val DATABASE_NAME = "sleep_quality.db"
    }
}