package com.joohnq.user.data.di

import app.cash.sqldelight.db.SqlDriver
import com.joohnq.domain.repository.UserPreferencesRepository
import com.joohnq.domain.repository.UserRepository
import com.joohnq.user.data.database.UserDatabase
import com.joohnq.user.data.repository.UserPreferencesRepositoryImpl
import com.joohnq.user.data.repository.UserRepositoryImpl
import com.joohnq.user.database.UserDatabaseSql
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val userDataModule = module {
    single<UserDatabaseSql> {
        val driver = get<SqlDriver>()
        UserDatabase(driver).invoke()
    }
    singleOf(::UserRepositoryImpl) bind UserRepository::class
    singleOf(::UserPreferencesRepositoryImpl) bind UserPreferencesRepository::class
}