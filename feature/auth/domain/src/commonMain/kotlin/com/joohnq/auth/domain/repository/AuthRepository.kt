package com.joohnq.auth.domain.repository

import com.joohnq.auth.domain.entity.AuthUser
import com.joohnq.auth.domain.entity.SignInWithGoogleResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun observeAuthState(): Flow<AuthUser?>
    suspend fun signOut()
    suspend fun signInWithEmailAndPassword(email: String, password: String)
    suspend fun signInWithGoogle(token: String, accessToken: String?): SignInWithGoogleResponse
    suspend fun signUpWithEmailAndPassword(email: String, password: String): String

    suspend fun refreshTokenIfNeeded(): String?
}