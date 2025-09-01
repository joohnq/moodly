package com.joohnq.permission

import androidx.compose.runtime.Composable

@Composable
actual fun createPermissionsManager(callback: PermissionCallback): PermissionsManager = PermissionsManager(callback)

actual class PermissionsManager actual constructor(
    private val callback: PermissionCallback,
) : PermissionHandler {
    @Composable
    override fun askPermission(permission: PermissionType) {
    }

    @Composable
    override fun isPermissionGranted(permission: PermissionType): Boolean = true

    @Composable
    override fun launchSettings() {
    }
}
