package com.joohnq.stress_level.data.di

import app.cash.sqldelight.db.SqlDriver
import com.joohnq.stress_level.data.database.StressLevelDatabase
import com.joohnq.stress_level.data.repository.StressLevelRepositoryImpl
import com.joohnq.stress_level.domain.repository.StressLevelRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val stressLevelDataModule = module {
    single {
        val driver = get<SqlDriver>()
        StressLevelDatabase(driver).invoke()
    }
    singleOf(::StressLevelRepositoryImpl) bind StressLevelRepository::class
}