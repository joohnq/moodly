package com.joohnq.security.data

import com.joohnq.security.domain.SecurityAuthentication
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCObjectVar
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import platform.Foundation.NSError
import platform.LocalAuthentication.LAContext
import platform.LocalAuthentication.LAPolicyDeviceOwnerAuthenticationWithBiometrics

actual class SecurityAuthenticationImpl : SecurityAuthentication {
    @OptIn(ExperimentalForeignApi::class)
    actual override fun isDeviceHasBiometric(): Boolean {
        val context = LAContext()

        memScoped {
            val error = alloc<ObjCObjectVar<NSError?>>()
            return context.canEvaluatePolicy(
                LAPolicyDeviceOwnerAuthenticationWithBiometrics,
                error.ptr
            )
        }
    }

    actual override fun authenticateWithFace(callback: (Boolean) -> Unit) {
        // Authenticate using biometric
        val context = LAContext()
        val reason = "Authenticate using face"

//        if (isDeviceHasBiometric()) {
        // Perform face authentication
        context.evaluatePolicy(
            LAPolicyDeviceOwnerAuthenticationWithBiometrics,
            localizedReason = reason
        ) { b: Boolean, nsError: NSError? ->
            callback(b)
            if (!b) {
                print(nsError?.localizedDescription ?: "Failed to authenticate")
            }
        }
//        }

        callback(false)
    }

}