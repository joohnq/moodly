package com.joohnq.moodapp.di

import androidx.room.RoomDatabase
import com.joohnq.moodapp.view.ScreenDimensions
import com.joohnq.moodapp.model.database.getMyDatabase
import com.joohnq.moodapp.model.MyDatabase
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val platformModule: Module = module {
    singleOf(::ScreenDimensions)
    singleOf<RoomDatabase.Builder<MyDatabase>>(::getMyDatabase)
}