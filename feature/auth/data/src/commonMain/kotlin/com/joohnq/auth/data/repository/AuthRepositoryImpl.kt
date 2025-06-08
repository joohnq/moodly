package com.joohnq.auth.data.repository

import com.joohnq.auth.domain.AuthException
import com.joohnq.auth.domain.entity.AuthUser
import com.joohnq.auth.domain.entity.SignInWithGoogleResponse
import com.joohnq.auth.domain.repository.AuthRepository
import dev.gitlive.firebase.auth.AuthResult
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class AuthRepositoryImpl(
    val auth: FirebaseAuth,
    val dispatcher: CoroutineDispatcher
) : AuthRepository {
    override fun observeAuthState(): Flow<AuthUser?> = auth.authStateChanged.map {
        it?.let {
            AuthUser(
                id = it.uid,
                name = it.displayName ?: "",
                email = it.email ?: "",
            )
        }
    }

    override suspend fun signOut() =
        withContext(dispatcher) {
            auth.signOut()
        }


    override suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ) {
        withContext(dispatcher) {
            auth.signInWithEmailAndPassword(email = email, password = password)
        }
    }

    override suspend fun signInWithGoogle(token: String, accessToken: String?): SignInWithGoogleResponse =
        withContext(dispatcher) {
            val credential =
                GoogleAuthProvider.credential(idToken = token, accessToken = accessToken)

            val signIn = auth.signInWithCredential(credential)
            val userId = signIn.user?.uid ?: throw AuthException.UserNotFound

            SignInWithGoogleResponse(
                id = userId,
                isNew = signIn.isNew(),
            )
        }

    override suspend fun signUpWithEmailAndPassword(
        email: String,
        password: String
    ): String =
        withContext(dispatcher) {
            val signUp = auth.createUserWithEmailAndPassword(email = email, password = password)
            signUp.user?.uid
                ?: throw AuthException.FailedToCreateUser
        }

    fun AuthResult.isNew(): Boolean {
        return additionalUserInfo?.isNewUser ?: true
    }

    override suspend fun refreshTokenIfNeeded(): String? = withContext(dispatcher) {
        val currentUser = auth.currentUser ?: throw AuthException.UserNotFound
        currentUser.getIdToken(forceRefresh = true)
    }
}