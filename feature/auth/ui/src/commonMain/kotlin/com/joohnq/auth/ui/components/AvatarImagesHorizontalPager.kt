package com.joohnq.auth.ui.components

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
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.avatar_illustration_description
import com.joohnq.shared_resources.remember.rememberAvatars
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

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
                contentDescription = stringResource(Res.string.avatar_illustration_description),
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(Dimens.Shape.Circle)
                    .border(
                        width = 8.dp,
                        color = Colors.White,
                        shape = Dimens.Shape.Circle
                    ),
            )
        }
    }
}

@Preview
@Composable
fun AvatarImagesHorizontalPagerPreview() {
    AvatarImagesHorizontalPager(
        avatars = rememberAvatars()
    )
}
