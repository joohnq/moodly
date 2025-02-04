package com.joohnq.home.ui.di

import com.joohnq.home.ui.presentation.viewmodel.DashboardViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val homeUiModule = module {
    singleOf(::DashboardViewModel)
}