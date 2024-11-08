package com.joohnq.moodapp.di

import androidx.room.RoomDatabase
import com.joohnq.moodapp.model.MyDatabase
import com.joohnq.moodapp.model.MyDatabaseInitializer
import com.joohnq.moodapp.view.ScreenDimensions
import com.joohnq.moodapp.view.screens.add.AddMoodViewModel
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
    single<RoomDatabase.Builder<MyDatabase>> { MyDatabaseInitializer(get()).init() }
    singleOf(::ScreenDimensions)
}

actual val viewModelModule: Module = module {
    singleOf(::UserPreferenceViewModel)
    singleOf(::OnboardingViewModel)
    singleOf(::StatsViewModel)
    singleOf(::UserViewModel)
    singleOf(::StressLevelViewModel)
    singleOf(::SleepQualityViewModel)
    singleOf(::AddMoodViewModel)
}