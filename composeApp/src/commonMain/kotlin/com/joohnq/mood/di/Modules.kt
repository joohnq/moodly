package com.joohnq.mood.di

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.joohnq.mood.data.LocalDatabase
import com.joohnq.mood.data.dao.HealthJournalRecordDAO
import com.joohnq.mood.data.dao.SleepQualityRecordDAO
import com.joohnq.mood.data.dao.StatsRecordDAO
import com.joohnq.mood.data.dao.StressLevelRecordDAO
import com.joohnq.mood.data.dao.UserDAO
import com.joohnq.mood.data.dao.UserPreferencesDAO
import com.joohnq.mood.data.repository.HealthJournalRepository
import com.joohnq.mood.data.repository.HealthJournalRepositoryImpl
import com.joohnq.mood.data.repository.SleepQualityRepository
import com.joohnq.mood.data.repository.SleepQualityRepositoryImpl
import com.joohnq.mood.data.repository.StatsRepository
import com.joohnq.mood.data.repository.StatsRepositoryImpl
import com.joohnq.mood.data.repository.StressLevelRepository
import com.joohnq.mood.data.repository.StressLevelRepositoryImpl
import com.joohnq.mood.data.repository.UserPreferencesRepository
import com.joohnq.mood.data.repository.UserPreferencesRepositoryImpl
import com.joohnq.mood.data.repository.UserRepository
import com.joohnq.mood.data.repository.UserRepositoryImpl
import com.joohnq.mood.ui.presentation.add_journaling_screen.AddJournalingViewModel
import com.joohnq.mood.ui.presentation.add_sleep_quality.AddSleepQualityViewModel
import com.joohnq.mood.ui.presentation.add_stats.AddStatViewModel
import com.joohnq.mood.ui.presentation.add_stress_level.AddStressLevelViewModel
import com.joohnq.mood.ui.presentation.all_journals.AllJournalViewModel
import com.joohnq.mood.ui.presentation.edit_journaling_screen.EditJournalingViewModel
import com.joohnq.mood.ui.presentation.onboarding.OnboardingViewModel
import com.joohnq.mood.viewmodel.GetUserNameViewModel
import com.joohnq.mood.viewmodel.HealthJournalViewModel
import com.joohnq.mood.viewmodel.SleepQualityViewModel
import com.joohnq.mood.viewmodel.StatsViewModel
import com.joohnq.mood.viewmodel.StressLevelViewModel
import com.joohnq.mood.viewmodel.UserPreferenceViewModel
import com.joohnq.mood.viewmodel.UserViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val viewModelModule = module {
    singleOf(::UserPreferenceViewModel)
    singleOf(::OnboardingViewModel)
    singleOf(::StatsViewModel)
    singleOf(::UserViewModel)
    singleOf(::StressLevelViewModel)
    singleOf(::SleepQualityViewModel)
    singleOf(::HealthJournalViewModel)
    singleOf(::AddJournalingViewModel)
    singleOf(::EditJournalingViewModel)
    singleOf(::AllJournalViewModel)
    singleOf(::AddSleepQualityViewModel)
    singleOf(::AddStatViewModel)
    singleOf(::AddStressLevelViewModel)
    singleOf(::GetUserNameViewModel)
}

val sharedModule = module {
    singleOf(Dispatchers::IO)
    singleOf(::BundledSQLiteDriver)
    singleOf(::UserPreferencesRepositoryImpl) bind UserPreferencesRepository::class
    singleOf(::UserRepositoryImpl) bind UserRepository::class
    singleOf(::StressLevelRepositoryImpl) bind StressLevelRepository::class
    singleOf(::SleepQualityRepositoryImpl) bind SleepQualityRepository::class
    singleOf(::StatsRepositoryImpl) bind StatsRepository::class
    singleOf(::HealthJournalRepositoryImpl) bind HealthJournalRepository::class
    single<LocalDatabase> {
        get<RoomDatabase.Builder<LocalDatabase>>()
            .setDriver(get<BundledSQLiteDriver>())
            .setQueryCoroutineContext(get<CoroutineDispatcher>())
            .build()
    }
    single<UserPreferencesDAO> {
        get<LocalDatabase>().userPreferencesDAO()
    }
    single<StatsRecordDAO> {
        get<LocalDatabase>().moodsDAO()
    }
    single<UserDAO> {
        get<LocalDatabase>().userDAO()
    }
    single<StressLevelRecordDAO> {
        get<LocalDatabase>().stressLevelRecordDAO()
    }
    single<SleepQualityRecordDAO> {
        get<LocalDatabase>().sleepQualityRecordDAO()
    }
    single<HealthJournalRecordDAO> {
        get<LocalDatabase>().healthJournalRecordDAO()
    }
}
