package com.joohnq.user.impl.data.di

import com.joohnq.api.repository.UserRepository
import com.joohnq.user.database.UserDatabaseSql
import com.joohnq.user.impl.data.database.UserDatabase
import com.joohnq.user.impl.data.driver.UserDriverFactory
import com.joohnq.user.impl.data.repository.UserRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val userDataModule =
    module {
        single<UserDatabaseSql> {
            val driver = get<UserDriverFactory>()
            UserDatabase(driver.createDriver()).invoke()
        }
        singleOf(::UserRepositoryImpl) bind UserRepository::class
    }
