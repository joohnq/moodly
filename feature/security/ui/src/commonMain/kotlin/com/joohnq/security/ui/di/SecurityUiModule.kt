package com.joohnq.security.ui.di

import com.joohnq.security.ui.presentation.pin.viewmodel.PinViewModel
import com.joohnq.security.ui.presentation.security.viewmodel.SecurityViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val securityUiModule: Module = module {
    viewModelOf(::PinViewModel)
    singleOf(::SecurityViewModel)
}