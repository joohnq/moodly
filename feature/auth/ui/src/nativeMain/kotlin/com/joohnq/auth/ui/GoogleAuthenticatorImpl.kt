package com.joohnq.auth.ui

import cocoapods.GoogleSignIn.GIDSignIn
import com.joohnq.auth.domain.GoogleAuthenticator
import com.joohnq.auth.domain.entity.OAuthUser
import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIApplication
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class GoogleAuthenticatorImpl: GoogleAuthenticator {
    @OptIn(ExperimentalForeignApi::class)
    override suspend fun signIn(): Result<OAuthUser> = suspendCoroutine { continuation ->
        try {
            val rootUiView = UIApplication.sharedApplication
                .keyWindow?.rootViewController ?: throw Exception("Root view is null")

            GIDSignIn.sharedInstance
                .signInWithPresentingViewController(rootUiView) { gidSignInResult, nsError ->
                    if (nsError != null) throw Exception(nsError.description.toString())

                    val idToken = gidSignInResult?.user?.idToken ?: throw Exception("ID Token is null")
                    val profile = gidSignInResult.user.profile

                    val auth = OAuthUser(
                        id = idToken.tokenString,
                        token = idToken.tokenString,
                        accessToken = gidSignInResult.user.accessToken.tokenString,
                        email = profile?.email,
                        name = profile?.name,
                        image = profile?.imageURLWithDimension(200UL)?.absoluteString
                    )

                    continuation.resume(Result.success(auth))
                }
        } catch (e: Exception) {
            println(e.message.toString())
            continuation.resume(Result.failure(e))
        }
    }
}