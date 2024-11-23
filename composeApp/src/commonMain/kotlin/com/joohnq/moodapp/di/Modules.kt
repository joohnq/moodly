package com.joohnq.moodapp.di

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.joohnq.moodapp.data.MyDatabase
import com.joohnq.moodapp.data.dao.HealthJournalRecordDAO
import com.joohnq.moodapp.data.dao.SleepQualityRecordDAO
import com.joohnq.moodapp.data.dao.StatsRecordDAO
import com.joohnq.moodapp.data.dao.StressLevelRecordDAO
import com.joohnq.moodapp.data.dao.UserDAO
import com.joohnq.moodapp.data.dao.UserPreferencesDAO
import com.joohnq.moodapp.data.repository.HealthJournalRepository
import com.joohnq.moodapp.data.repository.HealthJournalRepositoryImpl
import com.joohnq.moodapp.data.repository.SleepQualityRepository
import com.joohnq.moodapp.data.repository.SleepQualityRepositoryImpl
import com.joohnq.moodapp.data.repository.StatsRepository
import com.joohnq.moodapp.data.repository.StatsRepositoryImpl
import com.joohnq.moodapp.data.repository.StressLevelRepository
import com.joohnq.moodapp.data.repository.StressLevelRepositoryImpl
import com.joohnq.moodapp.data.repository.UserPreferencesRepository
import com.joohnq.moodapp.data.repository.UserPreferencesRepositoryImpl
import com.joohnq.moodapp.data.repository.UserRepository
import com.joohnq.moodapp.data.repository.UserRepositoryImpl
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

expect val viewModelModule: Module

val sharedModule = module {
    singleOf(Dispatchers::IO)
    singleOf(::BundledSQLiteDriver)
    singleOf(::UserPreferencesRepositoryImpl) bind UserPreferencesRepository::class
    singleOf(::UserRepositoryImpl) bind UserRepository::class
    singleOf(::StressLevelRepositoryImpl) bind StressLevelRepository::class
    singleOf(::SleepQualityRepositoryImpl) bind SleepQualityRepository::class
    singleOf(::StatsRepositoryImpl) bind StatsRepository::class
    singleOf(::HealthJournalRepositoryImpl) bind HealthJournalRepository::class
    single<MyDatabase> {
        get<RoomDatabase.Builder<MyDatabase>>()
            .setDriver(get<BundledSQLiteDriver>())
            .setQueryCoroutineContext(get<CoroutineDispatcher>())
            .build()
    }
    single<UserPreferencesDAO> {
        get<MyDatabase>().userPreferencesDAO()
    }
    single<StatsRecordDAO> {
        get<MyDatabase>().moodsDAO()
    }
    single<UserDAO> {
        get<MyDatabase>().userDAO()
    }
    single<StressLevelRecordDAO> {
        get<MyDatabase>().stressLevelRecordDAO()
    }
    single<SleepQualityRecordDAO> {
        get<MyDatabase>().sleepQualityRecordDAO()
    }
    single<HealthJournalRecordDAO> {
        get<MyDatabase>().healthJournalRecordDAO()
    }
}
