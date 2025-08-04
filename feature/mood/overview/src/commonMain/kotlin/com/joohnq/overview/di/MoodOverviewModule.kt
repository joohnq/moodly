package com.joohnq.overview.di

import com.joohnq.overview.presentation.MoodOverviewViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val moodOverviewModule: Module =
    module {
        viewModel<MoodOverviewViewModel> {
            MoodOverviewViewModel(
                getMoodsUseCase = get(),
                deleteMoodUseCase = get(),
                addMoodUseCase = get()
            )
        }
    }
