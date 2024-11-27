package com.joohnq.moodapp.di

import androidx.room.RoomDatabase
import com.joohnq.moodapp.data.LocalDatabase
import com.joohnq.moodapp.data.MyDatabaseInitializer
import com.joohnq.moodapp.ui.ScreenDimensions
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val platformModule: Module = module {
    singleOf(::ScreenDimensions)
    single<RoomDatabase.Builder<LocalDatabase>> { MyDatabaseInitializer().init() }
    singleOf<MyDatabaseInitializer>(::MyDatabaseInitializer)
}
