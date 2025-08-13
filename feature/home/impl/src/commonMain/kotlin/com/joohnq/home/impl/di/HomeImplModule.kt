package com.joohnq.home.impl.di

import com.joohnq.home.impl.ui.presentation.dashboard.DashboardViewModel
import org.koin.dsl.module

val homeImplModule =
    module {
        single<DashboardViewModel> {
            DashboardViewModel(
                getUserUseCase = get(),
                getMoodsUseCase = get(),
                getSelfJournalsUseCase = get(),
                getSleepQualitiesUseCase = get(),
                getAllStressLevelUseCase = get(),
                getGratefulnessUseCase = get()
            )
        }
    }
