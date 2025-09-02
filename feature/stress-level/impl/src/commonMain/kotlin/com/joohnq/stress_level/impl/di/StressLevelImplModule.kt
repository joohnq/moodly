package com.joohnq.stress_level.impl.di

import com.joohnq.stress_level.api.repository.StressLevelRepository
import com.joohnq.stress_level.database.StressLevelDatabaseSql
import com.joohnq.stress_level.impl.data.database.StressLevelDatabase
import com.joohnq.stress_level.impl.data.driver.StressLevelDriverFactory
import com.joohnq.stress_level.impl.data.repository.StressLevelRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val stressLevelImplModule =
    module {
        single<StressLevelDatabase> {
            val driver = get<StressLevelDriverFactory>().createDriver()
            StressLevelDatabase(driver)
        }
        single<StressLevelDatabaseSql> {
            val db = get<StressLevelDatabase>()
            db.invoke()
        }
        singleOf(::StressLevelRepositoryImpl) bind StressLevelRepository::class
        includes(stressLevelDriverFactoryModule)
    }
