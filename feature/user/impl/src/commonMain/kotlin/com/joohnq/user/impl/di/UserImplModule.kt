package com.joohnq.user.impl.di

import com.joohnq.api.repository.UserRepository
import com.joohnq.user.database.UserDatabaseSql
import com.joohnq.user.impl.data.database.UserDatabase
import com.joohnq.user.impl.data.driver.UserDriverFactory
import com.joohnq.user.impl.data.repository.UserRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val userImplModule =
    module {
        single<UserDatabase> {
            val driver = get<UserDriverFactory>().createDriver()
            UserDatabase(driver)
        }
        single<UserDatabaseSql> {
            val db = get<UserDatabase>()
            db.invoke()
        }
        singleOf(::UserRepositoryImpl) bind UserRepository::class
        includes(userDriverFactoryModule)
    }
