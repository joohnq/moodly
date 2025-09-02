package com.joohnq.mood.add.di

import com.joohnq.mood.add.data.database.MoodDatabase
import com.joohnq.mood.add.data.driver.MoodDriverFactory
import com.joohnq.mood.add.data.repository.MoodRepositoryImpl
import com.joohnq.mood.api.repository.MoodRepository
import com.joohnq.mood.database.MoodDatabaseSql
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val moodImplModule =
    module {
        single<MoodDatabase> {
            val driver = get<MoodDriverFactory>().createDriver()
            MoodDatabase(driver)
        }
        single<MoodDatabaseSql> {
            val db = get<MoodDatabase>()
            db.invoke()
        }
        singleOf(::MoodRepositoryImpl) bind MoodRepository::class
        includes(moodDriverFactoryModule)
    }
