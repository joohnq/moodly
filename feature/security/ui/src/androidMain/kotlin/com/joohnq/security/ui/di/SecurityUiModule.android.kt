package com.joohnq.security.ui.di

import com.joohnq.security.ui.presentation.pin.viewmodel.PINViewModel
import com.joohnq.security.ui.viewmodel.SecurityViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

actual val securityUiModule: Module = module {
    viewModelOf(::PINViewModel)
    viewModelOf(::SecurityViewModel)
}