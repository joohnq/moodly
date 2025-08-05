package com.joohnq.auth.impl.ui.presentation.avatar

import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import com.joohnq.auth.impl.ui.components.AlertMessageDialog
import com.joohnq.auth.impl.ui.components.ImageSourcePicker
import com.joohnq.permission.PermissionCallback
import com.joohnq.permission.PermissionStatus
import com.joohnq.permission.PermissionType
import com.joohnq.permission.createPermissionsManager
import com.joohnq.permission.rememberCameraManager
import com.joohnq.permission.rememberGalleryManager
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.cancel
import com.joohnq.shared_resources.permission_required
import com.joohnq.shared_resources.remember.rememberAvatars
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.shared_resources.settings
import com.joohnq.shared_resources.to_set_your_profile_picture
import com.joohnq.ui.observe
import com.joohnq.ui.sharedViewModel
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource

@Composable
fun AvatarScreen(
    navigateNext: () -> Unit,
    viewModel: AvatarViewModel = sharedViewModel(),
) {
    val snackBarState = rememberSnackBarState()
    val avatars = rememberAvatars()
    val pagerState = rememberPagerState(pageCount = { avatars.size })
    val scope = rememberCoroutineScope()
    val (state, dispatch) =
        viewModel.observe { sideEffect ->
            when (sideEffect) {
                AvatarContract.SideEffect.NavigateNext -> navigateNext()
                is AvatarContract.SideEffect.ShowError ->
                    launch { snackBarState.showSnackbar(sideEffect.message) }
            }
        }

    val permissionsManager =
        createPermissionsManager(
            object : PermissionCallback {
                override fun onPermissionStatus(
                    permissionType: PermissionType,
                    status: PermissionStatus,
                ) {
                    when (status) {
                        PermissionStatus.GRANTED -> {
                            when (permissionType) {
                                PermissionType.CAMERA ->
                                    dispatch(AvatarContract.Intent.ChangeLaunchCamera(true))

                                PermissionType.GALLERY ->
                                    dispatch(AvatarContract.Intent.ChangeLaunchGallery(true))
                            }
                        }

                        else -> {
                            dispatch(AvatarContract.Intent.ChangePermissionRationalDialog(true))
                        }
                    }
                }
            }
        )

    val cameraManager =
        rememberCameraManager {
            scope.launch {
                viewModel.onIntent(AvatarContract.Intent.ChangeImageBitmap(it?.toImageBitmap()))
            }
        }

    val galleryManager =
        rememberGalleryManager {
            scope.launch {
                viewModel.onIntent(AvatarContract.Intent.ChangeImageBitmap(it?.toImageBitmap()))
            }
        }

    if (state.imageSourceOptionDialog) {
        ImageSourcePicker(
            onDismissRequest = {
                dispatch(AvatarContract.Intent.ChangeImageSourceOptionDialog(false))
            },
            onGalleryRequest = {
                dispatch(AvatarContract.Intent.ChangeImageSourceOptionDialog(false))
                dispatch(AvatarContract.Intent.ChangeLaunchGallery(true))
            },
            onCameraRequest = {
                dispatch(AvatarContract.Intent.ChangeImageSourceOptionDialog(false))
                dispatch(AvatarContract.Intent.ChangeLaunchCamera(true))
            }
        )
    }

    if (state.launchGallery) {
        if (permissionsManager.isPermissionGranted(PermissionType.GALLERY)) {
            galleryManager.launch()
        } else {
            permissionsManager.askPermission(PermissionType.GALLERY)
        }
        dispatch(AvatarContract.Intent.ChangeLaunchGallery(false))
    }

    if (state.launchCamera) {
        if (permissionsManager.isPermissionGranted(PermissionType.CAMERA)) {
            cameraManager.launch()
        } else {
            permissionsManager.askPermission(PermissionType.CAMERA)
        }
        dispatch(AvatarContract.Intent.ChangeLaunchCamera(false))
    }

    if (state.launchSetting) {
        permissionsManager.launchSettings()
        dispatch(AvatarContract.Intent.ChangeLaunchSetting(false))
    }

    if (state.permissionRationalDialog) {
        AlertMessageDialog(
            title = stringResource(Res.string.permission_required),
            message = stringResource(Res.string.to_set_your_profile_picture),
            positiveButtonText = stringResource(Res.string.settings),
            negativeButtonText = stringResource(Res.string.cancel),
            onPositiveClick = {
                dispatch(AvatarContract.Intent.ChangePermissionRationalDialog(false))
                dispatch(AvatarContract.Intent.ChangeLaunchSetting(true))
            },
            onNegativeClick = {
                dispatch(AvatarContract.Intent.ChangePermissionRationalDialog(false))
            }
        )
    }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            viewModel.onIntent(AvatarContract.Intent.ChangeImageDrawableIndex(page))
        }
    }

    AvatarContent(
        snackBarState = snackBarState,
        state = state,
        avatars = avatars,
        onIntent = dispatch
    )
}