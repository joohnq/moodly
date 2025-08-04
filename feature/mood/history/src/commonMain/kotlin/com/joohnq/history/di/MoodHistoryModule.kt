package com.joohnq.history.di

import com.joohnq.history.presentation.MoodHistoryViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val moodHistoryModule: Module =
    module {
        viewModel<MoodHistoryViewModel> {
            MoodHistoryViewModel(
                getMoodsUseCase = get(),
                deleteMoodUseCase = get()
            )
        }
    }
