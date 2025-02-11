package com.joohnq.mood.data.di

import com.joohnq.mood.data.database.MoodDatabase
import com.joohnq.mood.data.driver.MoodDriverFactory
import com.joohnq.mood.data.repository.MoodRepositoryImpl
import com.joohnq.mood.database.MoodDatabaseSql
import com.joohnq.mood.domain.repository.MoodRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val moodDataModule = module {
    single<MoodDatabaseSql> {
        val driver = get<MoodDriverFactory>()
        MoodDatabase(driver.createDriver()).invoke()
    }
    singleOf(::MoodRepositoryImpl) bind MoodRepository::class
}