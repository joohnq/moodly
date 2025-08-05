package com.joohnq.security.impl.ui.di

import com.joohnq.security.impl.PinHelper
import com.joohnq.security.impl.ui.presentation.pin.PinViewModel
import com.joohnq.security.impl.ui.presentation.security.SecurityViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val securityUiModule: Module =
    module {
        singleOf(::PinHelper)
        viewModel<PinViewModel> {
            PinViewModel(
                updateSecurityUseCase = get(),
                pinHelper = get(),
            )
        }
        viewModel<SecurityViewModel> {
            SecurityViewModel(
                updateSecurityUseCase = get(),
                updateSkipSecurityUseCase = get()
            )
        }
    }