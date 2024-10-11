package com.joohnq.moodapp.di

import com.joohnq.moodapp.ScreenDimensions
import com.joohnq.moodapp.model.dao.UserPreferencesDAO
import com.joohnq.moodapp.model.getUserPreferencesDatabase
import com.joohnq.moodapp.viewmodel.UserPreferenceViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single { Dispatchers.IO }
    singleOf(::UserPreferenceViewModel)
    single<UserPreferencesDAO> { getUserPreferencesDatabase(get()).userPreferencesDAO() }
}