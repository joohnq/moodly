package com.joohnq.user.data.database

import com.joohnq.user.data.driver.UserDriverFactory
import com.joohnq.user.database.user.UserDatabaseSql

class UserDatabase(private val userDriverFactory: UserDriverFactory) {
    operator fun invoke(): UserDatabaseSql =
        UserDatabaseSql(userDriverFactory.createDriver())

    companion object {
        const val DATABASE_NAME = "user.db"
    }
}