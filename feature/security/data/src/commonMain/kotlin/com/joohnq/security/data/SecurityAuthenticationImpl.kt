package com.joohnq.security.data

import com.joohnq.security.domain.SecurityAuthentication

expect class SecurityAuthenticationImpl : SecurityAuthentication {
    override fun isDeviceHasBiometric(): Boolean
    override fun authenticateWithFace(callback: (Boolean) -> Unit)
}