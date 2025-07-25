package com.joohnq.auth.impl.presentation.avatar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.auth.impl.components.AvatarImagesHorizontalPager
import com.joohnq.auth.impl.presentation.avatar.event.AvatarEvent
import com.joohnq.auth.impl.presentation.avatar.viewmodel.AvatarState
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.button.PrimaryButton
import com.joohnq.shared_resources.components.layout.AppScaffoldLayout
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.components.modifier.drawDottedBorder
import com.joohnq.shared_resources.continue_word
import com.joohnq.shared_resources.or_upload_your_profile
import com.joohnq.shared_resources.profile
import com.joohnq.shared_resources.remember.rememberAvatars
import com.joohnq.shared_resources.remember.rememberSnackBarState
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
fun AvatarContent(
    snackBarState: SnackbarHostState = rememberSnackBarState(),
    state: AvatarState,
    avatars: List<DrawableResource> = rememberAvatars(),
    onEvent: (AvatarEvent) -> Unit = {},
) {
    AppScaffoldLayout(
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
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column {
                VerticalSpacer(32.dp)
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(Drawables.Icons.Filled.Target),
                        contentDescription = null,
                        modifier = Modifier.width(22.dp),
                        tint = Colors.Brown80
                    )
                    VerticalSpacer(10.dp)
                    AvatarImagesHorizontalPager(
                        avatars = avatars
                    )
                    VerticalSpacer(10.dp)
                    Icon(
                        painter = painterResource(Drawables.Icons.Filled.Target),
                        contentDescription = null,
                        modifier = Modifier.width(22.dp).rotate(-180f),
                        tint = Colors.Brown80
                    )
                }
                VerticalSpacer(40.dp)
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth().paddingHorizontalMedium()
                ) {
                    Text(
                        text = stringResource(Res.string.select_your_avatar),
                        style = TextStyles.text2xlExtraBold(),
                        color = Colors.Brown80
                    )
                    VerticalSpacer(12.dp)
                    Text(
                        text = stringResource(Res.string.we_have_a_set_of_customizable),
                        style = TextStyles.paragraphMd(),
                        color = Colors.Brown100Alpha64,
                        textAlign = TextAlign.Center
                    )
                    VerticalSpacer(40.dp)
                    IconButton(
                        onClick = { onEvent(AvatarEvent.OnPickAvatar) },
                        modifier = Modifier.size(96.dp)
                    ) {
                        if (state.imageBitmap != null) {
                            Image(
                                bitmap = state.imageBitmap,
                                contentDescription = stringResource(Res.string.profile),
                                modifier = Modifier.size(100.dp),
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            Box(
                                modifier = Modifier.size(64.dp)
                                    .drawDottedBorder(
                                        color = Colors.Gray40,
                                        shape = Dimens.Shape.Circle
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    painter = painterResource(Drawables.Icons.Outlined.Add),
                                    contentDescription = null,
                                    modifier = Modifier.size(32.dp),
                                    tint = Colors.Gray40
                                )
                            }
                        }
                    }
                    VerticalSpacer(16.dp)
                    Text(
                        text = stringResource(Res.string.or_upload_your_profile),
                        style = TextStyles.textMdBold(),
                        color = Colors.Brown100Alpha64
                    )
                }
            }
            PrimaryButton(
                modifier = Modifier.fillMaxWidth().paddingHorizontalMedium(),
                text = Res.string.continue_word,
                onClick = { onEvent(AvatarEvent.OnContinue) }
            )
        }
    }
}