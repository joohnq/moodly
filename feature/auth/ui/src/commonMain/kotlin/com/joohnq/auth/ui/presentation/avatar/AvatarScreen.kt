package com.joohnq.auth.ui.presentation.avatar

import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import com.joohnq.auth.ui.components.AlertMessageDialog
import com.joohnq.auth.ui.components.ImageSourceOptionDialog
import com.joohnq.auth.ui.presentation.avatar.event.AvatarEvent
import com.joohnq.auth.ui.presentation.avatar.state.AvatarState
import com.joohnq.auth.ui.saveImage
import com.joohnq.core.ui.CustomScreen
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.core.ui.toByteArray
import com.joohnq.permission.PermissionCallback
import com.joohnq.permission.PermissionStatus
import com.joohnq.permission.PermissionType
import com.joohnq.permission.createPermissionsManager
import com.joohnq.permission.rememberCameraManager
import com.joohnq.permission.rememberGalleryManager
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.user.ui.viewmodel.user.UserViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class AvatarScreen : CustomScreen<AvatarState>() {
    @Composable
    override fun Screen(): AvatarState {
        val snackBarState = remember { SnackbarHostState() }
        val images = listOf(
            Drawables.Images.AvatarCloud,
            Drawables.Images.AvatarTime,
            Drawables.Images.AvatarArrowUp,
            Drawables.Images.AvatarArrowDown,
            Drawables.Images.AvatarPieChart,
        )
        val pagerState = rememberPagerState(pageCount = { images.size })

        val userViewModel: UserViewModel = sharedViewModel()
        val scope = rememberCoroutineScope()
        var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }
        var imageSourceOptionDialog by remember { mutableStateOf(value = false) }
        var launchCamera by remember { mutableStateOf(value = false) }
        var launchGallery by remember { mutableStateOf(value = false) }
        var launchSetting by remember { mutableStateOf(value = false) }
        var permissionRationalDialog by remember { mutableStateOf(value = false) }
        val permissionsManager = createPermissionsManager(object : PermissionCallback {
            override fun onPermissionStatus(
                permissionType: PermissionType,
                status: PermissionStatus,
            ) {
                when (status) {
                    PermissionStatus.GRANTED -> {
                        when (permissionType) {
                            PermissionType.CAMERA -> launchCamera = true
                            PermissionType.GALLERY -> launchGallery = true
                        }
                    }

                    else -> {
                        permissionRationalDialog = true
                    }
                }
            }
        }
        )

        val cameraManager = rememberCameraManager {
            scope.launch {
                val bitmap = it?.toImageBitmap()
                imageBitmap = bitmap
            }
        }

        val galleryManager = rememberGalleryManager {
            scope.launch {
                val bitmap = it?.toImageBitmap()
                imageBitmap = bitmap
            }
        }
        if (imageSourceOptionDialog) {
            ImageSourceOptionDialog(onDismissRequest = {
                imageSourceOptionDialog = false
            }, onGalleryRequest = {
                imageSourceOptionDialog = false
                launchGallery = true
            }, onCameraRequest = {
                imageSourceOptionDialog = false
                launchCamera = true
            })
        }
        if (launchGallery) {
            if (permissionsManager.isPermissionGranted(PermissionType.GALLERY)) {
                galleryManager.launch()
            } else {
                permissionsManager.askPermission(PermissionType.GALLERY)
            }
            launchGallery = false
        }
        if (launchCamera) {
            if (permissionsManager.isPermissionGranted(PermissionType.CAMERA)) {
                cameraManager.launch()
            } else {
                permissionsManager.askPermission(PermissionType.CAMERA)
            }
            launchCamera = false
        }
        if (launchSetting) {
            permissionsManager.launchSettings()
            launchSetting = false
        }
        if (permissionRationalDialog) {
            AlertMessageDialog(title = "Permission Required",
                message = "To set your profile picture, please grant this permission. You can manage permissions in your device settings.",
                positiveButtonText = "Settings",
                negativeButtonText = "Cancel",
                onPositiveClick = {
                    permissionRationalDialog = false
                    launchSetting = true

                },
                onNegativeClick = {
                    permissionRationalDialog = false
                }
            )
        }

        fun setImage(
            imageBitmap: ImageBitmap,
            directory: String = "avatar",
        ): String {
            val biteArray = imageBitmap.toByteArray()
            return saveImage(
                directory = directory,
                fileName = "avatar.png",
                data = biteArray
            )
        }

        fun onEvent(event: AvatarEvent) {
            when (event) {
                AvatarEvent.OnPickAvatar -> {
                    imageSourceOptionDialog = true
                }

                AvatarEvent.OnContinue -> {
                    if (imageBitmap != null) {
                        scope.launch(Dispatchers.IO) {
                            val res = setImage(imageBitmap!!)
                            println("RESSSSSSSSSSSSSS: $res")
                        }
                    }
                }
            }
        }



        return AvatarState(
            snackBarState = snackBarState,
            pagerState = pagerState,
            images = images,
            onEvent = ::onEvent,
            imageBitmap = imageBitmap
        )
    }

    @Composable
    override fun UI(state: AvatarState) = AvatarUI(state)
}