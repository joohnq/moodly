package com.joohnq.moodapp.di

import androidx.room.RoomDatabase
import com.joohnq.moodapp.ScreenDimensions
import com.joohnq.moodapp.model.UserPreferencesDatabase
import com.joohnq.moodapp.model.database.getUserPreferencesDatabase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val platformModule = module {
    single<RoomDatabase.Builder<UserPreferencesDatabase>> { getUserPreferencesDatabase(get()) }
    singleOf(::ScreenDimensions)
}