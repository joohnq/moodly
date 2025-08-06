package com.joohnq.security.impl.di

import com.joohnq.security.api.SecurityPreference
import com.joohnq.security.impl.PinHelper
import com.joohnq.security.impl.data.SecurityPreferenceImpl
import com.joohnq.security.impl.ui.presentation.pin.PinViewModel
import com.joohnq.security.impl.ui.presentation.security.SecurityViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val securityImplModule: Module =
    module {
        singleOf(::SecurityPreferenceImpl) bind SecurityPreference::class
        singleOf(::PinHelper)
        viewModel<PinViewModel> {
            PinViewModel(
                updateSecurityUseCase = get(),
                pinHelper = get()
            )
        }
        viewModel<SecurityViewModel> {
            SecurityViewModel(
                updateSecurityUseCase = get(),
                updateSkipSecurityUseCase = get()
            )
        }
        includes(securityAuthenticationModule)
    }
