package com.joohnq.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import com.joohnq.database.converters.LocalDateTimeConverter
import com.joohnq.domain.entity.StatsRecord
import com.joohnq.domain.entity.User
import com.joohnq.domain.entity.UserPreferences
import com.joohnq.health_journal.data.dao.HealthJournalRecordDAO
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.mood.data.StatsRecordConverter
import com.joohnq.mood.data.repository.StatsRecordDAO
import com.joohnq.sleep_quality.data.converter.SleepInfluencesConverter
import com.joohnq.sleep_quality.data.converter.SleepQualityRecordConverter
import com.joohnq.sleep_quality.data.dao.SleepQualityRecordDAO
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.joohnq.stress_level.data.StressLevelRecordConverter
import com.joohnq.stress_level.data.StressorsConverter
import com.joohnq.stress_level.data.repository.StressLevelRecordDAO
import com.joohnq.stress_level.domain.entity.StressLevelRecord
import com.joohnq.user.data.converter.UserConverter
import com.joohnq.user.data.dao.UserDAO
import com.joohnq.user.data.dao.UserPreferencesDAO

@Database(
    entities = [StatsRecord::class, User::class, UserPreferences::class, StressLevelRecord::class, SleepQualityRecord::class, HealthJournalRecord::class],
    version = DatabaseConstants.DATABASE_VERSION
)
@ConstructedBy(LocalDatabaseConstructor::class)
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
expect object LocalDatabaseConstructor :
    RoomDatabaseConstructor<LocalDatabase> {
    override fun initialize(): LocalDatabase
}

expect class LocalDatabaseInitializer {
    fun init(): RoomDatabase.Builder<LocalDatabase>
}