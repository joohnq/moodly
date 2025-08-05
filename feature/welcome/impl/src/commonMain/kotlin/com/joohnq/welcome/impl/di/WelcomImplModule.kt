package com.joohnq.welcome.impl.di

import com.joohnq.welcome.impl.ui.presentation.welcome.WelcomeViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val welcomeImplModule: Module =
    module {
        viewModel {
            WelcomeViewModel(
                updateSkipWelcomeUseCase = get()
            )
        }
    }