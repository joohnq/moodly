package com.joohnq.mood.impl.ui.di

import com.joohnq.mood.impl.ui.presentation.add.AddMoodViewModel
import com.joohnq.mood.impl.ui.presentation.history.MoodHistoryViewModel
import com.joohnq.mood.impl.ui.presentation.overview.MoodOverviewViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val moodUiModule: Module =
    module {
        single<AddMoodViewModel> {
            AddMoodViewModel(
                addMoodUseCase = get()
            )
        }
        single<MoodOverviewViewModel> {
            MoodOverviewViewModel(
                getMoodsUseCase = get(),
                addMoodUseCase = get(),
                deleteMoodUseCase = get()
            )
        }
        single<MoodHistoryViewModel> {
            MoodHistoryViewModel(
                getMoodsUseCase = get(),
                deleteMoodUseCase = get()
            )
        }
    }
