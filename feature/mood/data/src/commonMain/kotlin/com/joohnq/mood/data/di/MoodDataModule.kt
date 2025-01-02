package com.joohnq.mood.data.di

import com.joohnq.mood.data.data_source.StatsDataSourceImpl
import com.joohnq.mood.data.database.StatsDatabase
import com.joohnq.mood.data.repository.StatsRepositoryImpl
import com.joohnq.mood.domain.data_source.StatsDataSource
import com.joohnq.mood.domain.repository.StatsRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val moodDataModule = module {
    singleOf(::StatsDatabase)
    singleOf(::StatsDataSourceImpl) bind StatsDataSource::class
    singleOf(::StatsRepositoryImpl) bind StatsRepository::class
}