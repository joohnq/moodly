package com.joohnq.home.impl.ui.di

import com.joohnq.home.impl.ui.presentation.viewmodel.DashboardViewModel
import org.koin.dsl.module

val homeUiModule =
    module {
        single<DashboardViewModel> {
            DashboardViewModel(
                userViewModel = get(),
                moodOverviewViewModel = get(),
                freudScoreViewModel = get(),
                selfJournalOverviewViewModel = get(),
                sleepQualityViewModel = get(),
                stressLevelViewModel = get()
            )
        }
    }
