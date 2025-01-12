package com.joohnq.mood.data.di

import com.joohnq.mood.data.database.StatsDatabase
import com.joohnq.mood.data.driver.StatsDriverFactory
import com.joohnq.mood.data.repository.StatsRepositoryImpl
import com.joohnq.mood.database.StatsDatabaseSql
import com.joohnq.mood.domain.repository.StatsRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val moodDataModule = module {
    single<StatsDatabaseSql> {
        val driver = get<StatsDriverFactory>()
        StatsDatabase(driver.createDriver()).invoke()
    }
    singleOf(::StatsRepositoryImpl) bind StatsRepository::class
}