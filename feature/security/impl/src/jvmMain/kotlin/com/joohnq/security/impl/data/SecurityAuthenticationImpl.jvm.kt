package com.joohnq.security.impl.data

import com.joohnq.security.api.SecurityAuthentication

actual class SecurityAuthenticationImpl : SecurityAuthentication {
    actual override fun isDeviceHasBiometric(): Boolean = false

    actual override fun authenticateWithFace(callback: (Boolean) -> Unit) {
        callback(false)
    }
}
