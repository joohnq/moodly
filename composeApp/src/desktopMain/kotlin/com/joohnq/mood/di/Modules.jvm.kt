package com.joohnq.mood.di

import androidx.room.RoomDatabase
import com.joohnq.mood.ScreenDimensions
import com.joohnq.mood.data.LocalDatabase
import com.joohnq.mood.data.MyDatabaseInitializer
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val platformModule = module {
    single<RoomDatabase.Builder<LocalDatabase>> { MyDatabaseInitializer().init() }
    singleOf(::ScreenDimensions)
}
