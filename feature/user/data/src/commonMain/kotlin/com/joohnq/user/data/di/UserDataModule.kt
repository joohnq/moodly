package com.joohnq.user.data.di

import com.joohnq.domain.repository.UserDataSource
import com.joohnq.domain.repository.UserPreferencesDataSource
import com.joohnq.domain.repository.UserPreferencesRepository
import com.joohnq.domain.repository.UserRepository
import com.joohnq.user.data.data_source.UserDataSourceImpl
import com.joohnq.user.data.data_source.UserPreferencesDataSourceImpl
import com.joohnq.user.data.database.UserDatabase
import com.joohnq.user.data.repository.UserPreferencesRepositoryImpl
import com.joohnq.user.data.repository.UserRepositoryImpl
import com.joohnq.user.database.UserDatabaseSql
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val userDataModule = module {
    single<UserDatabaseSql> {
        UserDatabase(get()).invoke()
    }
    singleOf(::UserDataSourceImpl) bind (UserDataSource::class)
    singleOf(::UserPreferencesDataSourceImpl) bind (UserPreferencesDataSource::class)
    singleOf(::UserRepositoryImpl) bind (UserRepository::class)
    singleOf(::UserPreferencesRepositoryImpl) bind (UserPreferencesRepository::class)
}