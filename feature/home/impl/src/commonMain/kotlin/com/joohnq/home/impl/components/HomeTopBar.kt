package com.joohnq.home.impl.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.api.getNow
import com.joohnq.api.mapper.toFormattedDateString
import com.joohnq.api.constant.UserFileStorageConstants
import com.joohnq.api.entity.ImageType
import com.joohnq.api.entity.User
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.image.ProfileImage
import com.joohnq.shared_resources.components.image.CacheImage
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
) {
    val avatars = rememberAvatars()
    Column(
        modifier = Modifier.fillMaxSize().background(
            color = Colors.Brown80, shape = Dimens.Shape.BottomLarge
        ).padding(20.dp).then(modifier)
    ) {
        Row {
            Row(
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(Drawables.Icons.Outlined.Calendar),
                    modifier = Modifier.size(20.dp),
                    tint = Colors.Brown40,
                    contentDescription = null
                )
                Text(
                    text = getNow().date.toFormattedDateString(),
                    style = TextStyles.textSmBold(),
                    color = Colors.White
                )
            }
        }
        VerticalSpacer(15.dp)
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            user.image?.let {
                when (user.imageType) {
                    ImageType.DEVICE -> {
                        CacheImage(
                            directory = UserFileStorageConstants.AVATAR_DIRECTORY,
                            fileName = UserFileStorageConstants.AVATAR_FILE_NAME
                        )
                    }

                    ImageType.DRAWABLE -> {
                        ProfileImage(
                            painterResource(avatars[it.toInt()])
                        )
                    }
                }
            }
            Text(
                text = stringResource(Res.string.greeting, user.name),
                style = TextStyles.headingSmExtraBold(),
                color = Colors.White
            )
        }
    }
}