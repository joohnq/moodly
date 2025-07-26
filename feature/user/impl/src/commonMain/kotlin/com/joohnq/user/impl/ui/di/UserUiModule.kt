package com.joohnq.user.impl.ui.di

import com.joohnq.user.impl.ui.viewmodel.UserViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val userUiModule: Module =
    module {
        single<UserViewModel> {
            UserViewModel(
                addUserUseCase = get(),
                updateUserUseCase = get(),
                getUserUseCase = get(),
                updateUserNameUseCase = get(),
                updateUserImageBitmapUseCase = get(),
                updateUserImageDrawableUseCase = get(),
            )
        }
    }