package com.joohnq.stress_level.data.di

import com.joohnq.stress_level.data.data_source.StressLevelDataSourceImpl
import com.joohnq.stress_level.data.database.StressLevelDatabase
import com.joohnq.stress_level.data.repository.StressLevelRepositoryImpl
import com.joohnq.stress_level.database.StressLevelDatabaseSql
import com.joohnq.stress_level.domain.data_source.StressLevelDataSource
import com.joohnq.stress_level.domain.repository.StressLevelRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val stressLevelDataModule = module {
    single<StressLevelDatabaseSql> {
        StressLevelDatabase(get()).invoke()
    }
    singleOf(::StressLevelDataSourceImpl) bind (StressLevelDataSource::class)
    singleOf(::StressLevelRepositoryImpl) bind (StressLevelRepository::class)
}