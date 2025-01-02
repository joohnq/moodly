package com.joohnq.mood.data.di

import com.joohnq.mood.data.data_source.StatsDataSourceImpl
import com.joohnq.mood.data.database.StatsDatabase
import com.joohnq.mood.data.repository.StatsRepositoryImpl
import com.joohnq.mood.domain.repository.StatsDataSource
import com.joohnq.mood.domain.repository.StatsRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val moodDataModule = module {
    single { StatsDatabase(get()).invoke() }
    singleOf(::StatsDataSourceImpl) bind (StatsDataSource::class)
    singleOf(::StatsRepositoryImpl) bind (StatsRepository::class)
}