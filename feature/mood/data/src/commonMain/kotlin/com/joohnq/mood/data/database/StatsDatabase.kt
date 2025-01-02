package com.joohnq.mood.data.database

import com.joohnq.core.database.Database
import com.joohnq.mood.data.driver.StatsDriverFactory
import com.joohnq.mood.database.StatsDatabaseSql

class StatsDatabase(private val driverFactory: StatsDriverFactory) : Database<StatsDatabaseSql>() {
    override operator fun invoke(): StatsDatabaseSql =
        StatsDatabaseSql(driverFactory.createDriver())

    companion object {
        const val DATABASE_NAME = "stats.db"
    }
}