package com.joohnq.moodapp.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.joohnq.moodapp.model.MyDatabase
import com.joohnq.moodapp.model.dao.StatsRecordDAO
import com.joohnq.moodapp.model.dao.UserDAO
import com.joohnq.moodapp.model.dao.UserPreferencesDAO
import com.joohnq.moodapp.model.getMyDatabase
import com.joohnq.moodapp.viewmodel.MoodsViewModel
import com.joohnq.moodapp.viewmodel.UserPreferenceViewModel
import com.joohnq.moodapp.viewmodel.UserViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    singleOf(Dispatchers::IO)
    viewModelOf(::UserPreferenceViewModel)
    viewModelOf(::MoodsViewModel)
    viewModelOf(::UserViewModel)
    singleOf(::BundledSQLiteDriver)
    single<MyDatabase> {
        getMyDatabase(get(), get(), get())
    }
    single<UserPreferencesDAO> {
        get<MyDatabase>().userPreferencesDAO()
    }
    single<StatsRecordDAO> {
        get<MyDatabase>().moodsDAO()
    }
    single<UserDAO> {
        get<MyDatabase>().userDAO()
    }
}
