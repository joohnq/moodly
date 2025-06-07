package com.joohnq.auth.domain

import com.joohnq.auth.domain.entity.OAuthUser

interface GoogleAuthenticator {
    suspend fun signIn(): Result<OAuthUser>
}