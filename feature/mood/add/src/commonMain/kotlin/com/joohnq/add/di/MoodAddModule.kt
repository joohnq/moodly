package com.joohnq.add.di

import com.joohnq.add.presentation.AddMoodViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val moodAddModule: Module =
    module {
        single<AddMoodViewModel> {
            AddMoodViewModel(
                addMoodUseCase = get()
            )
        }
    }
