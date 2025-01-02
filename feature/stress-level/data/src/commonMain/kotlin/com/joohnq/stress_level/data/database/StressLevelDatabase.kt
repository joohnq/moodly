package com.joohnq.stress_level.data.database

import com.joohnq.core.database.Database
import com.joohnq.stress_level.data.driver.StressLevelDriverFactory
import com.joohnq.stress_level.database.StressLevelDatabaseSql

class StressLevelDatabase(private val driverFactory: StressLevelDriverFactory) :
    Database<StressLevelDatabaseSql>() {
    override operator fun invoke(): StressLevelDatabaseSql =
        StressLevelDatabaseSql(driverFactory.createDriver())

    companion object {
        const val DATABASE_NAME = "stress_level.db"
    }
}