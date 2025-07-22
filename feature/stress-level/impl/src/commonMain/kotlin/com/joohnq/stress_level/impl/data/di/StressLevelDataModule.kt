package com.joohnq.stress_level.data.di

import com.joohnq.stress_level.data.database.StressLevelDatabase
import com.joohnq.stress_level.data.driver.StressLevelDriverFactory
import com.joohnq.stress_level.data.repository.StressLevelRepositoryImpl
import com.joohnq.stress_level.database.StressLevelDatabaseSql
import com.joohnq.stress_level.api.repository.StressLevelRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val stressLevelDataModule = module {
    single<StressLevelDatabaseSql> {
        val driver = get<StressLevelDriverFactory>()
        StressLevelDatabase(driver.createDriver()).invoke()
    }
    singleOf(::StressLevelRepositoryImpl) bind StressLevelRepository::class
}