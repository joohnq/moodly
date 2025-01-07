package com.joohnq.auth.ui.presentation.avatar

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.or_upload_your_profile
import com.joohnq.shared_resources.profile_setup
import com.joohnq.shared_resources.select_your_avatar
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.shared_resources.we_have_a_set_of_customizable
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun AvatarUI(state: AvatarState) {
    Scaffold(
        containerColor = Colors.Brown10,
        snackbarHost = { SnackbarHost(hostState = state.snackBarState) },
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        val avatarSize = 180.dp
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
        ) {
            VerticalSpacer(10.dp)
            Text(
                text = stringResource(Res.string.profile_setup),
                style = TextStyles.TextXlExtraBold(),
                color = Colors.Brown80,
                modifier = Modifier.paddingHorizontalMedium()
            )
            VerticalSpacer(32.dp)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(Drawables.Icons.SimpleTarget),
                    contentDescription = null,
                    modifier = Modifier.width(22.dp),
                    tint = Colors.Brown80
                )
                VerticalSpacer(10.dp)
                BoxWithConstraints {
                    HorizontalPager(
                        state = state.pagerState,
                        pageSize = PageSize.Fixed(avatarSize),
                        contentPadding = PaddingValues(horizontal = (maxWidth / 2) - (avatarSize / 2)),
                        pageSpacing = 20.dp
                    ) { page ->
                        Image(
                            painter = painterResource(state.images[page]),
                            contentDescription = null,
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
                VerticalSpacer(10.dp)
                Icon(
                    painter = painterResource(Drawables.Icons.SimpleTarget),
                    contentDescription = null,
                    modifier = Modifier.width(22.dp).rotate(-180f),
                    tint = Colors.Brown80
                )
            }
            VerticalSpacer(40.dp)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.paddingHorizontalMedium()
            ) {
                Text(
                    text = stringResource(Res.string.select_your_avatar),
                    style = TextStyles.Text2xlExtraBold(),
                    color = Colors.Brown80
                )
                VerticalSpacer(12.dp)
                Text(
                    text = stringResource(Res.string.we_have_a_set_of_customizable),
                    style = TextStyles.ParagraphMd(),
                    color = Colors.Brown100Alpha64,
                    textAlign = TextAlign.Center
                )
                VerticalSpacer(40.dp)
                IconButton(
                    onClick = { state.onEvent(AvatarEvent.OnPickAvatar) },
                    modifier = Modifier.size(96.dp)
                ) {
                    Icon(
                        painter = painterResource(Drawables.Icons.PhotoPicker),
                        contentDescription = null,
                        modifier = Modifier.size(96.dp),
                        tint = Colors.Brown80
                    )
                }
                VerticalSpacer(16.dp)
                Text(
                    text = stringResource(Res.string.or_upload_your_profile),
                    style = TextStyles.TextMdBold(),
                    color = Colors.Brown100Alpha64
                )
            }
        }
    }
}

@Preview
@Composable
fun AvatarPreview() {
    AvatarUI(
        AvatarState(
            snackBarState = remember { SnackbarHostState() },
            pagerState = rememberPagerState(initialPage = 0, pageCount = { 5 }),
        )
    )
}