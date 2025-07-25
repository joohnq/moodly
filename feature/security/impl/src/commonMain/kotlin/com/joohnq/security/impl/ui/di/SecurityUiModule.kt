package com.joohnq.security.impl.ui.di

import com.joohnq.security.impl.ui.presentation.pin.PinViewModel
import com.joohnq.security.impl.ui.presentation.security.SecurityViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val securityUiModule: Module = module {
    single<PinViewModel> {
        PinViewModel()
    }
    single<SecurityViewModel> {
        SecurityViewModel(
            getSecurityUseCase = get(),
            updateSecurityUseCase = get(),
        )
    }
}