package com.joohnq.home.impl.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import com.joohnq.api.constant.UserFileStorageConstants
import com.joohnq.api.entity.ImageType
import com.joohnq.api.entity.User
import com.joohnq.api.getNow
import com.joohnq.api.mapper.LocalDateMapper.toFormattedDateString
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.image.CacheImage
import com.joohnq.shared_resources.components.image.ProfileImage
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.greeting
import com.joohnq.shared_resources.remember.rememberAvatars
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun HomeTopBar(
    modifier: Modifier = Modifier,
    user: User,
    freudScore: Int,
    onNavigateToFreudScore: () -> Unit = {},
) {
    val avatars = rememberAvatars()
    Card(
        onClick = { onNavigateToFreudScore() },
        colors = CardColors(
            containerColor = Colors.Brown60,
            contentColor = Colors.White,
            disabledContainerColor = Colors.Brown60,
            disabledContentColor = Colors.White
        ),
        shape = Dimens.Shape.BottomLarge
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, end = 20.dp, bottom = 20.dp, start = 20.dp)
                    .then(modifier),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                user.image?.let {
                    when (user.imageType) {
                        ImageType.DEVICE -> {
                            CacheImage(
                                modifier = Modifier.size(32.dp),
                                directory = UserFileStorageConstants.AVATAR_DIRECTORY,
                                fileName = UserFileStorageConstants.AVATAR_FILE_NAME
                            )
                        }

                        ImageType.DRAWABLE -> {
                            ProfileImage(
                                modifier = Modifier.size(32.dp),
                                painterResource(avatars[it.toInt()])
                            )
                        }
                    }
                }
                Text(
                    text = getNow().date.toFormattedDateString(),
                    style = TextStyles.textMdSemiBold(),
                    color = Colors.White
                )
                Box {}
            }
            VerticalSpacer(15.dp)
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(Dimens.Shape.Circle)
                        .background(color = Colors.Brown70),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = freudScore.toString(),
                        style = TextStyles.headingSmBold(),
                        color = Colors.White
                    )
                }
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(Res.string.greeting, user.name),
                        style = TextStyles.textXlBold(),
                        color = Colors.White
                    )
                }
                Icon(
                    painter = painterResource(Drawables.Icons.Outlined.ArrowOpen),
                    contentDescription = null,
                    tint = Colors.White,
                    modifier = Modifier.size(Dimens.Icon).rotate(180f)
                )
            }
        }
    }
}