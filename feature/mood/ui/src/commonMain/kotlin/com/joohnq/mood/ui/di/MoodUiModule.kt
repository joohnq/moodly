package com.joohnq.mood.ui.di

import com.joohnq.mood.ui.presentation.add_mood.viewmodel.AddMoodViewModel
import com.joohnq.mood.ui.viewmodel.MoodViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val moodUiModule: Module = module {
    singleOf(::MoodViewModel)
    singleOf(::AddMoodViewModel)
}