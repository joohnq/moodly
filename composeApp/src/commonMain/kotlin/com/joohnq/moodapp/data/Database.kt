package com.joohnq.moodapp.data

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import com.joohnq.moodapp.data.converters.LocalDateTimeConverter
import com.joohnq.moodapp.data.converters.SleepInfluencesConverter
import com.joohnq.moodapp.data.converters.SleepQualityRecordConverter
import com.joohnq.moodapp.data.converters.StatsRecordConverter
import com.joohnq.moodapp.data.converters.StressLevelRecordConverter
import com.joohnq.moodapp.data.converters.StressorsConverter
import com.joohnq.moodapp.data.converters.UserConverter
import com.joohnq.moodapp.data.dao.HealthJournalRecordDAO
import com.joohnq.moodapp.data.dao.SleepQualityRecordDAO
import com.joohnq.moodapp.data.dao.StatsRecordDAO
import com.joohnq.moodapp.data.dao.StressLevelRecordDAO
import com.joohnq.moodapp.data.dao.UserDAO
import com.joohnq.moodapp.data.dao.UserPreferencesDAO
import com.joohnq.moodapp.domain.HealthJournalRecord
import com.joohnq.moodapp.domain.SleepQualityRecord
import com.joohnq.moodapp.domain.StatsRecord
import com.joohnq.moodapp.domain.StressLevelRecord
import com.joohnq.moodapp.domain.User
import com.joohnq.moodapp.domain.UserPreferences

@Database(
    entities = [StatsRecord::class, User::class, UserPreferences::class, StressLevelRecord::class, SleepQualityRecord::class, HealthJournalRecord::class],
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
abstract class LocalDatabase : RoomDatabase() {
    abstract fun moodsDAO(): StatsRecordDAO
    abstract fun userDAO(): UserDAO
    abstract fun userPreferencesDAO(): UserPreferencesDAO
    abstract fun stressLevelRecordDAO(): StressLevelRecordDAO
    abstract fun sleepQualityRecordDAO(): SleepQualityRecordDAO
    abstract fun healthJournalRecordDAO(): HealthJournalRecordDAO
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object MyDatabaseConstructor :
    RoomDatabaseConstructor<LocalDatabase> {
    override fun initialize(): LocalDatabase
}

expect class MyDatabaseInitializer {
    fun init(): RoomDatabase.Builder<LocalDatabase>
}