package com.joohnq.home.impl.di

import com.joohnq.home.impl.presentation.viewmodel.DashboardViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val homeUiModule = module {
    singleOf(::DashboardViewModel)
}