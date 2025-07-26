package com.joohnq.user.impl.data.database

import app.cash.sqldelight.db.SqlDriver
import com.joohnq.database.Database
import com.joohnq.user.database.UserDatabaseSql

class UserDatabase(
    private val driver: SqlDriver
) : Database<UserDatabaseSql>() {
    override operator fun invoke(): UserDatabaseSql = UserDatabaseSql(driver)

    companion object {
        const val DATABASE_NAME = "user.db"
    }
}
