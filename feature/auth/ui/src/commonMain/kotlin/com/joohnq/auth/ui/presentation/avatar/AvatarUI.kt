package com.joohnq.auth.ui.presentation.avatar

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.auth.ui.presentation.avatar.event.AvatarEvent
import com.joohnq.auth.ui.presentation.avatar.viewmodel.AvatarState
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.ContinueButton
import com.joohnq.shared_resources.components.ScaffoldSnackBar
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
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun AvatarImagesHorizontalPager(images: List<DrawableResource>) {
    val avatarSize = 180.dp
    val pagerState = rememberPagerState(pageCount = { images.size })
    BoxWithConstraints {
        HorizontalPager(
            state = pagerState,
            pageSize = PageSize.Fixed(avatarSize),
            contentPadding = PaddingValues(horizontal = (maxWidth / 2) - (avatarSize / 2)),
            pageSpacing = 20.dp
        ) { page ->
            Image(
                painter = painterResource(images[page]),
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
}

@Composable
fun AvatarUI(
    snackBarState: SnackbarHostState,
    pagerState: PagerState,
    images: List<DrawableResource> = emptyList(),
    onEvent: (AvatarEvent) -> Unit = {},
    avatarState: AvatarState,
) {
    ScaffoldSnackBar(
        containerColor = Colors.Brown10,
        snackBarHostState = snackBarState,
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(bottom = 20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Column {
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
                    AvatarImagesHorizontalPager(
                        images = images
                    )
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
                        onClick = { onEvent(AvatarEvent.OnPickAvatar) },
                        modifier = Modifier.size(96.dp)
                    ) {
                        if (avatarState.imageBitmap != null) {
                            Image(
                                bitmap = avatarState.imageBitmap,
                                contentDescription = "Profile",
                                modifier = Modifier.size(100.dp),
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            Icon(
                                painter = painterResource(Drawables.Icons.PhotoPicker),
                                contentDescription = null,
                                modifier = Modifier.size(96.dp),
                                tint = Colors.Brown80
                            )
                        }
                    }
                    VerticalSpacer(16.dp)
                    Text(
                        text = stringResource(Res.string.or_upload_your_profile),
                        style = TextStyles.TextMdBold(),
                        color = Colors.Brown100Alpha64
                    )
                }
            }
            ContinueButton(
                modifier = Modifier.fillMaxWidth().paddingHorizontalMedium(),
                onClick = { onEvent(AvatarEvent.OnContinue) }
            )
        }
    }
}
