package com.joohnq.moodapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.sunildhiman90.kmauth.core.KMAuthInitializer
import com.sunildhiman90.kmauth.core.KMAuthPlatformContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        FirebaseApp.initializeApp(this)

        KMAuthInitializer.initWithContext(
            webClientId = BuildKonfig.GOOGLE_CLIENT_ID,
            kmAuthPlatformContext = KMAuthPlatformContext(this)
        )

        setContent {
            App()
        }
    }
}