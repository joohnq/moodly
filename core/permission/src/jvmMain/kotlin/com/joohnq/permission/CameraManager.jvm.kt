package com.joohnq.permission

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
actual fun rememberCameraManager(onResult: (SharedImage?) -> Unit): CameraManager =
    remember {
        CameraManager { }
    }

actual class CameraManager actual constructor(
    private val onLaunch: () -> Unit,
) {
    actual fun launch() {
        onLaunch()
    }
}
