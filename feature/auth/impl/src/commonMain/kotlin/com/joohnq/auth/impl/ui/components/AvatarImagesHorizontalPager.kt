package com.joohnq.auth.impl.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun AvatarImagesHorizontalPager(avatars: List<DrawableResource>) {
    val avatarSize = 180.dp
    val pagerState = rememberPagerState(pageCount = { avatars.size })

    BoxWithConstraints {
        HorizontalPager(
            state = pagerState,
            pageSize = PageSize.Fixed(avatarSize),
            contentPadding = PaddingValues(horizontal = (maxWidth / 2) - (avatarSize / 2)),
            pageSpacing = 20.dp
        ) { page ->
            Image(
                painter = painterResource(avatars[page]),
                contentDescription = null,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .clip(Dimens.Shape.Circle)
                        .border(
                            width = 8.dp,
                            color = Colors.White,
                            shape = Dimens.Shape.Circle
                        )
            )
        }
    }
}
