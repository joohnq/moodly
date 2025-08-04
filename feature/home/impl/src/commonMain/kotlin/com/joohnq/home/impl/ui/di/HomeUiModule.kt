package com.joohnq.home.impl.ui.di

import com.joohnq.home.impl.ui.presentation.viewmodel.DashboardViewModel
import org.koin.dsl.module

val homeUiModule =
    module {
        single<DashboardViewModel> {
            DashboardViewModel(
                getUserUseCase = get(),
                getMoodsUseCase = get(),
                getSelfJournalsUseCase = get(),
                getSleepQualitiesUseCase = get(),
                getAllStressLevelUseCase = get()
            )
        }
    }
