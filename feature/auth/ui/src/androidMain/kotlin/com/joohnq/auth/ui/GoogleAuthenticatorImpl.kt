package com.joohnq.auth.ui

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.joohnq.auth.domain.GoogleAuthenticator
import com.joohnq.auth.domain.entity.OAuthUser

class GoogleAuthenticatorImpl(
    private val context: Context,
    private val credentialManager: CredentialManager
) : GoogleAuthenticator {

    override suspend fun signIn(): Result<OAuthUser> =
        try {
            val googleIdOption = GetGoogleIdOption.Builder()
                .setServerClientId("40912754044-lfmvdhjhss9ijnofm575glhbk9hokq9c.apps.googleusercontent.com")
                .setAutoSelectEnabled(false)
                .setFilterByAuthorizedAccounts(false)
                .build()
            val request = GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build()

            val result = credentialManager.getCredential(context, request)
            val credential = result.credential

            if (credential !is CustomCredential || credential.type != GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) throw Exception(
                "Credential is not GoogleIdTokenCredential"
            )

            val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)

            val auth = OAuthUser(
                id = googleIdTokenCredential.idToken,
                token = googleIdTokenCredential.idToken,
                accessToken = null,
                email = "",
                name = googleIdTokenCredential.displayName,
                image = googleIdTokenCredential.profilePictureUri.toString()
            )

            Result.success(auth)
        } catch (e: Exception) {
            Result.failure(e)
        }
}