package com.joohnq.auth.ui.presentation.avatar

import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.joohnq.core.ui.CustomScreen
import com.joohnq.shared_resources.theme.Drawables
import org.jetbrains.compose.resources.DrawableResource

data class AvatarState(
    val snackBarState: SnackbarHostState,
    val pagerState: PagerState,
    val images: List<DrawableResource> = emptyList(),
    val onEvent: (AvatarEvent) -> Unit = {},
)

sealed class AvatarEvent {
    data object OnPickAvatar : AvatarEvent()
}

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

        fun onEvent(event: AvatarEvent) {
            when (event) {
                AvatarEvent.OnPickAvatar -> {}
            }
        }

        return AvatarState(
            snackBarState = snackBarState,
            pagerState = pagerState,
            images = images,
            onEvent = ::onEvent
        )
    }

    @Composable
    override fun UI(state: AvatarState) = AvatarUI(state)
}