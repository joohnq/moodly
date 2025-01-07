package com.joohnq.auth.ui.di

import com.joohnq.auth.ui.presentation.user_name.viewmodel.UserNameViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

actual val authUiModule: Module = module {
    viewModelOf(::UserNameViewModel)
}