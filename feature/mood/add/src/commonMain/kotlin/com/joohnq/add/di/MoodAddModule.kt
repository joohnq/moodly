package com.joohnq.add.di

import com.joohnq.add.presentation.AddMoodViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val moodAddModule: Module =
    module {
        viewModel<AddMoodViewModel> {
            AddMoodViewModel(
                addMoodUseCase = get()
            )
        }
    }
