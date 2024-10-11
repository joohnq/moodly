package com.joohnq.moodapp.model

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabase.Builder
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.joohnq.moodapp.model.dao.UserPreferencesDAO
import com.joohnq.moodapp.model.entities.UserPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

@Database(
    entities = [UserPreferences::class],
    version = 1
)
@ConstructedBy(UserPreferencesDatabaseConstructor::class)
abstract class UserPreferencesDatabase : RoomDatabase() {
    abstract fun userPreferencesDAO(): UserPreferencesDAO
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object UserPreferencesDatabaseConstructor : RoomDatabaseConstructor<UserPreferencesDatabase> {
    override fun initialize(): UserPreferencesDatabase
}

fun getUserPreferencesDatabase(
    builder: Builder<UserPreferencesDatabase>
): UserPreferencesDatabase {
    return builder
        .fallbackToDestructiveMigrationOnDowngrade(true)
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}
