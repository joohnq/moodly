package com.joohnq.moodapp.di

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.joohnq.moodapp.data.LocalDatabase
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
import com.joohnq.moodapp.ui.presentation.add_journaling_screen.AddJournalingViewModel
import com.joohnq.moodapp.ui.presentation.add_sleep_quality.AddSleepQualityViewModel
import com.joohnq.moodapp.ui.presentation.add_stats.AddStatViewModel
import com.joohnq.moodapp.ui.presentation.add_stress_level.AddStressLevelViewModel
import com.joohnq.moodapp.ui.presentation.all_journals.AllJournalViewModel
import com.joohnq.moodapp.ui.presentation.edit_journaling_screen.EditJournalingViewModel
import com.joohnq.moodapp.ui.presentation.onboarding.OnboardingViewModel
import com.joohnq.moodapp.viewmodel.GetUserNameViewModel
import com.joohnq.moodapp.viewmodel.HealthJournalViewModel
import com.joohnq.moodapp.viewmodel.SleepQualityViewModel
import com.joohnq.moodapp.viewmodel.StatsViewModel
import com.joohnq.moodapp.viewmodel.StressLevelViewModel
import com.joohnq.moodapp.viewmodel.UserPreferenceViewModel
import com.joohnq.moodapp.viewmodel.UserViewModel
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
