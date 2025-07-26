package com.joohnq.security.impl.data

import com.joohnq.security.api.SecurityAuthentication
import kotlinx.cinterop.*
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
        val context = LAContext()
        val reason = "Authenticate using face"

        context.evaluatePolicy(
            LAPolicyDeviceOwnerAuthenticationWithBiometrics,
            localizedReason = reason
        ) { b: Boolean, nsError: NSError? ->
            callback(b)
            if (!b) {
                print(nsError?.localizedDescription ?: "Failed to authenticate")
            }
        }
        callback(false)
    }
}
