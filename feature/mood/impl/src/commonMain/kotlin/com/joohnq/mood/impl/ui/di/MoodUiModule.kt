package com.joohnq.mood.impl.ui.di

import com.joohnq.mood.impl.ui.presentation.add_mood.AddMoodViewModel
import com.joohnq.mood.impl.ui.presentation.mood.MoodViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val moodUiModule: Module = module {
    single<AddMoodViewModel>{
        AddMoodViewModel()
    }
    single<MoodViewModel> {
        MoodViewModel(
            getMoodsUseCase = get(),
            addMoodUseCase = get(),
            deleteMoodUseCase = get(),
        )
    }
}