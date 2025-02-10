package com.joohnq.user.ui.di

import com.joohnq.user.ui.viewmodel.UserViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val userUiModule: Module = module {
    singleOf(::UserViewModel)
}