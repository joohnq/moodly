package com.joohnq.auth.domain.repository

import com.joohnq.auth.domain.entity.AuthUser
import kotlinx.coroutines.flow.Flow

typealias IsNew = Boolean

interface AuthRepository {
    fun observeAuthState(): Flow<AuthUser?>
    suspend fun signOut()
    suspend fun signInWithEmailAndPassword(email: String, password: String)
    suspend fun signInWithGoogle(token: String, accessToken: String?): Boolean
    suspend fun signInWithApple(token: String, accessToken: String?): Boolean
    suspend fun signUpWithEmailAndPassword(email: String, password: String): String

    suspend fun refreshTokenIfNeeded(): String?
}