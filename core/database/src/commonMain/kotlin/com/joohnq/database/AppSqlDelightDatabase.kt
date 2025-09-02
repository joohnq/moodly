package com.joohnq.database

import app.cash.sqldelight.ColumnAdapter
import app.cash.sqldelight.db.SqlDriver

val listOfStringsAdapter =
    object : ColumnAdapter<List<String>, String> {
        override fun decode(databaseValue: String) =
            if (databaseValue.isEmpty()) {
                listOf()
            } else {
                databaseValue.split(",")
            }

        override fun encode(value: List<String>) = value.joinToString(separator = ",")
    }

class AppSqlDelightDatabase(
    private val driver: SqlDriver,
) : Database<AppDatabaseSql>() {
    override operator fun invoke(): AppDatabaseSql =
        AppDatabaseSql(
            driver = driver,
            sleep_qualitiesAdapter = Sleep_qualities.Adapter(listOfStringsAdapter),
            stress_levelsAdapter = Stress_levels.Adapter(listOfStringsAdapter)
        )

    @Suppress("EmptyFunctionBlock")
    override fun drop() {}

    companion object {
        const val DATABASE_NAME = "moodly.db"
    }
}
