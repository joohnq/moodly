package com.joohnq.sleep_quality.data.di

import com.joohnq.sleep_quality.data.database.SleepQualityDatabase
import com.joohnq.sleep_quality.data.repository.SleepQualityRepositoryImpl
import com.joohnq.sleep_quality.domain.repository.SleepQualityRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val sleepQualityDataModule = module {
    singleOf(::SleepQualityDatabase)
    singleOf(SleepQualityDatabase::invoke)
    singleOf(::SleepQualityRepositoryImpl) bind SleepQualityRepository::class
}