package com.joohnq.user.data.database

import com.joohnq.user.data.driver.UserPreferencesDriverFactory
import com.joohnq.user.database.user_preferences.UserPreferencesDatabaseSql

class UserPreferencesDatabase(private val userPreferencesDriverFactory: UserPreferencesDriverFactory) {
    operator fun invoke(): UserPreferencesDatabaseSql =
        UserPreferencesDatabaseSql(userPreferencesDriverFactory.createDriver())

    companion object {
        const val DATABASE_NAME = "user_preferences.db"
    }
}