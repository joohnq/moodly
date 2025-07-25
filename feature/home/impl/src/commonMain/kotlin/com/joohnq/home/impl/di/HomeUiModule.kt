package com.joohnq.home.impl.di

import com.joohnq.home.impl.presentation.viewmodel.DashboardViewModel
import org.koin.dsl.module

val homeUiModule = module {
    single<DashboardViewModel> {
        DashboardViewModel(
            userViewModel = get(),
            moodViewModel = get(),
            freudScoreViewModel = get(),
            selfJournalViewModel = get(),
            sleepQualityViewModel = get(),
            stressLevelViewModel = get()
        )
    }
}