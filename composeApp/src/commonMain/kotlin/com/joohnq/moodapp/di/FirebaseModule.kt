package com.joohnq.moodapp.di

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.firestore
import org.koin.core.module.Module
import org.koin.dsl.module

val firebaseModule: Module = module {
    single<FirebaseAuth> { Firebase.auth }
    single<FirebaseFirestore> { Firebase.firestore }
}