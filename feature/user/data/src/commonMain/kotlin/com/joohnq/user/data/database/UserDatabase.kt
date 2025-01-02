package com.joohnq.user.data.database

import com.joohnq.core.database.Database
import com.joohnq.user.data.driver.UserDriverFactory
import com.joohnq.user.database.UserDatabaseSql

class UserDatabase(private val driverFactory: UserDriverFactory) : Database<UserDatabaseSql>() {
    override operator fun invoke(): UserDatabaseSql =
        UserDatabaseSql(driverFactory.createDriver())

    companion object {
        const val DATABASE_NAME = "user.db"
    }
}