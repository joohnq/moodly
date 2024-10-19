package com.joohnq.moodapp.model

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabase.Builder
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.joohnq.moodapp.model.converters.StatsRecordConverter
import com.joohnq.moodapp.model.converters.UserConverter
import com.joohnq.moodapp.model.dao.StatsRecordDAO
import com.joohnq.moodapp.model.dao.UserDAO
import com.joohnq.moodapp.model.dao.UserPreferencesDAO
import com.joohnq.moodapp.model.entities.StatsRecord
import com.joohnq.moodapp.model.entities.User
import com.joohnq.moodapp.model.entities.UserPreferences
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

@Database(
    entities = [StatsRecord::class, User::class, UserPreferences::class],
    version = DatabaseConstants.DATABASE_VERSION
)
@ConstructedBy(MyDatabaseConstructor::class)
@TypeConverters(
    StatsRecordConverter::class,
    UserConverter::class,
)
abstract class MyDatabase : RoomDatabase() {
    abstract fun moodsDAO(): StatsRecordDAO
    abstract fun userDAO(): UserDAO
    abstract fun userPreferencesDAO(): UserPreferencesDAO
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object MyDatabaseConstructor : RoomDatabaseConstructor<MyDatabase> {
    override fun initialize(): MyDatabase
}

fun getMyDatabase(
    builder: Builder<MyDatabase>,
    bundledSQLiteDriver: BundledSQLiteDriver,
    ioDispatcher: CoroutineDispatcher
): MyDatabase {
    return builder
        .setDriver(bundledSQLiteDriver)
        .setQueryCoroutineContext(ioDispatcher)
        .build()
}
