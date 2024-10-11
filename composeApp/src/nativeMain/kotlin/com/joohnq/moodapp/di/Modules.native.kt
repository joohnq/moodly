package com.joohnq.moodapp.di

import androidx.room.RoomDatabase
import com.joohnq.moodapp.ScreenDimensions
import com.joohnq.moodapp.database.getUserPreferencesDatabase
import com.joohnq.moodapp.model.UserPreferencesDatabase
import com.joohnq.moodapp.model.dao.UserPreferencesDAO
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val platformModule: Module = module {
    singleOf(::ScreenDimensions)
    single<RoomDatabase.Builder<UserPreferencesDatabase>>{
        getUserPreferencesDatabase()
    }
}