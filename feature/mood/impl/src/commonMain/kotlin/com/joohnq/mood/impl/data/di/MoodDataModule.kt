package com.joohnq.mood.impl.data.di

import com.joohnq.mood.api.repository.MoodRepository
import com.joohnq.mood.database.MoodDatabaseSql
import com.joohnq.mood.impl.data.database.MoodDatabase
import com.joohnq.mood.impl.data.driver.MoodDriverFactory
import com.joohnq.mood.impl.data.repository.MoodRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val moodDataModule =
    module {
        single<MoodDatabaseSql> {
            val driver = get<MoodDriverFactory>()
            MoodDatabase(driver.createDriver()).invoke()
        }
        singleOf(::MoodRepositoryImpl) bind MoodRepository::class
    }
