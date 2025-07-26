package com.joohnq.permission

interface PermissionCallback {
    fun onPermissionStatus(
        permissionType: PermissionType,
        status: PermissionStatus,
    )
}
