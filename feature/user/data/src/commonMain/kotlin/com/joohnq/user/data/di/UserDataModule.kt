package com.joohnq.user.data.di

import com.joohnq.domain.repository.UserDataSource
import com.joohnq.domain.repository.UserPreferencesDataSource
import com.joohnq.domain.repository.UserPreferencesRepository
import com.joohnq.domain.repository.UserRepository
import com.joohnq.user.data.data_source.UserDataSourceImpl
import com.joohnq.user.data.data_source.UserPreferencesDataSourceImpl
import com.joohnq.user.data.database.UserDatabase
import com.joohnq.user.data.database.UserPreferencesDatabase
import com.joohnq.user.data.driver.UserDriverFactory
import com.joohnq.user.data.driver.UserPreferencesDriverFactory
import com.joohnq.user.data.repository.UserPreferencesRepositoryImpl
import com.joohnq.user.data.repository.UserRepositoryImpl
import com.joohnq.user.database.user.UserDatabaseSql
import com.joohnq.user.database.user_preferences.UserPreferencesDatabaseSql
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module(includes = [UserDriverFactoryModule::class])
@ComponentScan
class UserDataModule {
    @Single
    fun provideUserDatabase(
        userDriverFactory: UserDriverFactory,
    ): UserDatabaseSql = UserDatabase(userDriverFactory = userDriverFactory).invoke()

    @Single
    fun provideUserPreferencesDatabase(
        userPreferencesDriverFactory: UserPreferencesDriverFactory,
    ): UserPreferencesDatabaseSql =
        UserPreferencesDatabase(userPreferencesDriverFactory = userPreferencesDriverFactory).invoke()

    @Single(binds = [UserDataSource::class])
    fun provideUserDatabase(
        database: UserDatabaseSql,
    ): UserDataSource = UserDataSourceImpl(database = database)

    @Single(binds = [UserPreferencesDataSource::class])
    fun provideUserPreferencesDatabase(
        database: UserPreferencesDatabaseSql,
    ): UserPreferencesDataSource = UserPreferencesDataSourceImpl(database = database)

    @Single(binds = [UserRepository::class])
    fun provideUserRepository(
        userDataSource: UserDataSource,
    ): UserRepository = UserRepositoryImpl(
        userDataSource = userDataSource
    )

    @Single(binds = [UserPreferencesRepository::class])
    fun provideUserPreferencesRepository(
        userPreferencesDataSource: UserPreferencesDataSource,
    ): UserPreferencesRepository = UserPreferencesRepositoryImpl(
        userPreferencesDataSource = userPreferencesDataSource
    )
}