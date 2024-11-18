package com.joohnq.moodapp.model

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import com.joohnq.moodapp.entities.SleepQualityRecord
import com.joohnq.moodapp.entities.StatsRecord
import com.joohnq.moodapp.entities.StressLevelRecord
import com.joohnq.moodapp.entities.User
import com.joohnq.moodapp.entities.UserPreferences
import com.joohnq.moodapp.model.converters.LocalDateTimeConverter
import com.joohnq.moodapp.model.converters.SleepInfluencesConverter
import com.joohnq.moodapp.model.converters.SleepQualityRecordConverter
import com.joohnq.moodapp.model.converters.StatsRecordConverter
import com.joohnq.moodapp.model.converters.StressLevelRecordConverter
import com.joohnq.moodapp.model.converters.StressorsConverter
import com.joohnq.moodapp.model.converters.UserConverter
import com.joohnq.moodapp.model.dao.SleepQualityRecordDAO
import com.joohnq.moodapp.model.dao.StatsRecordDAO
import com.joohnq.moodapp.model.dao.StressLevelRecordDAO
import com.joohnq.moodapp.model.dao.UserDAO
import com.joohnq.moodapp.model.dao.UserPreferencesDAO

@Database(
    entities = [StatsRecord::class, User::class, UserPreferences::class, StressLevelRecord::class, SleepQualityRecord::class],
    version = DatabaseConstants.DATABASE_VERSION
)
@ConstructedBy(MyDatabaseConstructor::class)
@TypeConverters(
    StatsRecordConverter::class,
    UserConverter::class,
    SleepQualityRecordConverter::class,
    StressLevelRecordConverter::class,
    LocalDateTimeConverter::class,
    StressorsConverter::class,
    SleepInfluencesConverter::class
)
abstract class MyDatabase : RoomDatabase() {
    abstract fun moodsDAO(): StatsRecordDAO
    abstract fun userDAO(): UserDAO
    abstract fun userPreferencesDAO(): UserPreferencesDAO
    abstract fun stressLevelRecordDAO(): StressLevelRecordDAO
    abstract fun sleepQualityRecordDAO(): SleepQualityRecordDAO
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object MyDatabaseConstructor : RoomDatabaseConstructor<MyDatabase> {
    override fun initialize(): MyDatabase
}

expect class MyDatabaseInitializer {
    fun init(): RoomDatabase.Builder<MyDatabase>
}