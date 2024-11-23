package com.joohnq.moodapp.di

import androidx.room.RoomDatabase
import com.joohnq.moodapp.data.LocalDatabase
import com.joohnq.moodapp.data.MyDatabaseInitializer
import com.joohnq.moodapp.ui.ScreenDimensions
import com.joohnq.moodapp.viewmodel.HealthJournalViewModel
import com.joohnq.moodapp.viewmodel.OnboardingViewModel
import com.joohnq.moodapp.viewmodel.SleepQualityViewModel
import com.joohnq.moodapp.viewmodel.StatsViewModel
import com.joohnq.moodapp.viewmodel.StressLevelViewModel
import com.joohnq.moodapp.viewmodel.UserPreferenceViewModel
import com.joohnq.moodapp.viewmodel.UserViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val platformModule = module {
    single<RoomDatabase.Builder<LocalDatabase>> { MyDatabaseInitializer(get()).init() }
    singleOf(::ScreenDimensions)
}

actual val viewModelModule: Module = module {
    singleOf(::UserPreferenceViewModel)
    singleOf(::OnboardingViewModel)
    singleOf(::StatsViewModel)
    singleOf(::UserViewModel)
    singleOf(::StressLevelViewModel)
    singleOf(::SleepQualityViewModel)
    singleOf(::HealthJournalViewModel)
}