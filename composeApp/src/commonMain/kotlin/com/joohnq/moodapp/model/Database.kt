package com.joohnq.moodapp.model

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabase.Builder
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.joohnq.moodapp.model.dao.MoodsDAO
import com.joohnq.moodapp.model.dao.UserDAO
import com.joohnq.moodapp.model.dao.UserPreferencesDAO
import com.joohnq.moodapp.view.entities.MoodDb
import com.joohnq.moodapp.view.entities.MoodDbConverters
import com.joohnq.moodapp.view.entities.User
import com.joohnq.moodapp.view.entities.UserConverters
import com.joohnq.moodapp.view.entities.UserPreferences

@Database(
    entities = [MoodDb::class, User::class, UserPreferences::class],
    version = 1
)
@ConstructedBy(MyDatabaseConstructor::class)
@TypeConverters(MoodDbConverters::class, UserConverters::class)
abstract class MyDatabase : RoomDatabase() {
    abstract fun moodsDAO(): MoodsDAO
    abstract fun userDAO(): UserDAO
    abstract fun userPreferencesDAO(): UserPreferencesDAO
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object MyDatabaseConstructor : RoomDatabaseConstructor<MyDatabase> {
    override fun initialize(): MyDatabase
}

fun getMyDatabase(
    builder: Builder<MyDatabase>,
    bundledSQLiteDriver: BundledSQLiteDriver
): MyDatabase {
    return builder
        .setDriver(bundledSQLiteDriver)
//        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}
