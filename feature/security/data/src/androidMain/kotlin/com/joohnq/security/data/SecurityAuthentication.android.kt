package com.joohnq.security.data

import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_WEAK
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.core.app.ActivityCompat.startActivityForResult
import com.joohnq.security.domain.SecurityAuthentication

actual class SecurityAuthenticationImpl(private val activity: AppCompatActivity) :
    SecurityAuthentication {
    @RequiresApi(Build.VERSION_CODES.R)
    actual override fun isDeviceHasBiometric(): Boolean {
        val manager = BiometricManager.from(activity)
        when (manager.canAuthenticate(BIOMETRIC_STRONG or BIOMETRIC_WEAK)) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                Log.d("`FaceAuthenticator.kt`", "App can authenticate using biometrics.")
                return true
            }

            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                Log.e("`FaceAuthenticator.kt`", "No biometric features available on this device.")
                return false
            }

            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                Log.e("`FaceAuthenticator.kt`", "Biometric features are currently unavailable.")
                return false
            }

            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                Log.e(
                    "`FaceAuthenticator.kt`",
                    "Prompts the user to create credentials that your app accepts."
                )
                val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                    putExtra(
                        Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                        BIOMETRIC_STRONG or BIOMETRIC_WEAK
                    )
                }

                startActivityForResult(activity, enrollIntent, 100, null)
            }

            BiometricManager.BIOMETRIC_ERROR_SECURITY_UPDATE_REQUIRED -> {
                Log.e(
                    "`FaceAuthenticator.kt`",
                    "Security vulnerability has been discovered and the sensor is unavailable until a security update has addressed this issue."
                )
            }

            BiometricManager.BIOMETRIC_ERROR_UNSUPPORTED -> {
                Log.e(
                    "`FaceAuthenticator.kt`",
                    "The user can't authenticate because the specified options are incompatible with the current Android version."
                )
            }

            BiometricManager.BIOMETRIC_STATUS_UNKNOWN -> {
                Log.e(
                    "`FaceAuthenticator.kt`",
                    "Unable to determine whether the user can authenticate"
                )
            }
        }
        return false
    }

    @RequiresApi(Build.VERSION_CODES.P)
    actual override fun authenticateWithFace(callback: (Boolean) -> Unit) {

        // Create prompt Info to set prompt details
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Authentication using biometric")
            .setSubtitle("Authenticate using face/fingerprint")
            .setAllowedAuthenticators(BIOMETRIC_STRONG or BIOMETRIC_WEAK or DEVICE_CREDENTIAL)
            .build()

        // Create biometricPrompt object to get authentication callback result
        val biometricPrompt = BiometricPrompt(activity, activity.mainExecutor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(
                    errorCode: Int,
                    errString: CharSequence,
                ) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(activity, "Authentication error: $errString", Toast.LENGTH_SHORT)
                        .show()
                    callback(false)
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult,
                ) {
                    super.onAuthenticationSucceeded(result)
                    Toast.makeText(activity, "Authentication succeeded!", Toast.LENGTH_SHORT).show()
                    callback(true)
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(activity, "Authentication failed", Toast.LENGTH_SHORT).show()
                    callback(false)
                }
            })

        //Authenticate using biometric prompt
        biometricPrompt.authenticate(promptInfo)
    }
}
