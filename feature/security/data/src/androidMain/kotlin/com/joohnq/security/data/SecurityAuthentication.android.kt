package com.joohnq.security.data

import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.*
import androidx.biometric.BiometricPrompt
import androidx.core.app.ActivityCompat.startActivityForResult
import com.joohnq.security.domain.SecurityAuthentication
import com.joohnq.security.domain.SecurityResult
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

actual class SecurityAuthenticationImpl(private val activity: AppCompatActivity) :
    SecurityAuthentication {
    private val _securityResult = Channel<SecurityResult>()
    val securityResult = _securityResult.receiveAsFlow()

    @RequiresApi(Build.VERSION_CODES.R)
    actual override fun isDeviceHasBiometric(): Boolean {
        val manager = BiometricManager.from(activity)
        when (manager.canAuthenticate(BIOMETRIC_STRONG or BIOMETRIC_WEAK)) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                return true
            }

            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                _securityResult.trySend(SecurityResult.FeatureUnavailable)
                return false
            }

            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                _securityResult.trySend(SecurityResult.HardwareUnavailable)
                return false
            }

            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
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
                _securityResult.trySend(SecurityResult.AuthenticationFailed)
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
        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Authentication using biometric")
            .setSubtitle("Authenticate using face/fingerprint")
            .setAllowedAuthenticators(BIOMETRIC_STRONG or BIOMETRIC_WEAK or DEVICE_CREDENTIAL)
            .build()

        val biometricPrompt = BiometricPrompt(
            activity, activity.mainExecutor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(
                    errorCode: Int,
                    errString: CharSequence,
                ) {
                    super.onAuthenticationError(errorCode, errString)
                    callback(false)
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult,
                ) {
                    super.onAuthenticationSucceeded(result)
                    callback(true)
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    callback(false)
                }
            })

        biometricPrompt.authenticate(promptInfo)
    }
}
