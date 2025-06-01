package com.joohnq.auth.data.di

import com.joohnq.auth.data.repository.AuthRepositoryImpl
import com.joohnq.auth.domain.repository.AuthRepository
import dev.gitlive.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val authDataModule: Module = module {
    single {
        val auth = get<FirebaseAuth>()
        val dispatcher = get<CoroutineDispatcher>(qualifier = named("IO"))
        AuthRepositoryImpl(
            auth = auth,
            dispatcher = dispatcher
        )
    } bind AuthRepository::class
}