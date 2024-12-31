package com.joohnq.mood.data.database

import com.joohnq.mood.data.driver.StatsDriverFactory
import com.joohnq.mood.database.StatsDatabaseSql

class StatsDatabase(private val statsDriverFactory: StatsDriverFactory) {
    operator fun invoke(): StatsDatabaseSql =
        StatsDatabaseSql(statsDriverFactory.createDriver())

    companion object {
        const val DATABASE_NAME = "stats.db"
    }
}