package com.joohnq.home.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.joohnq.domain.constant.UserFileStorageConstants
import com.joohnq.domain.entity.ImageType
import com.joohnq.domain.entity.User
import com.joohnq.domain.entity.UserImage
import com.joohnq.domain.getNow
import com.joohnq.domain.mapper.capitalize
import com.joohnq.domain.mapper.getPrimaryName
import com.joohnq.domain.mapper.toFormattedDateString
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.AvatarImage
import com.joohnq.shared_resources.components.ImageCache
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.greeting
import com.joohnq.shared_resources.remember.rememberAvatars
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HomeTopBar(
    modifier: Modifier = Modifier,
    user: User,
) {
    val avatars = rememberAvatars()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Colors.Brown80, shape = Dimens.Shape.BottomLarge
            )
            .padding(20.dp)
            .then(modifier)
    ) {
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
                text = getNow().date.toFormattedDateString().capitalize(),
                style = TextStyles.textSmBold(),
                color = Colors.White
            )
        }
        VerticalSpacer(15.dp)
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            user.image?.let {
                when (it.type) {
                    ImageType.DEVICE -> {
                        ImageCache(
                            directory = UserFileStorageConstants.AVATAR_DIRECTORY,
                            fileName = UserFileStorageConstants.AVATAR_FILE_NAME
                        )
                    }

                    ImageType.DRAWABLE -> {
                        it.image?.let { image ->
                            AvatarImage(
                                painterResource(avatars[image.toInt()])
                            )
                        }
                    }

                    ImageType.URL -> {
                        AsyncImage(
                            model = it.image,
                            contentDescription = null,
                            modifier = Modifier
                                .size(64.dp)
                                .background(color = Colors.White, shape = Dimens.Shape.Circle)
                                .clip(Dimens.Shape.Circle),
                            contentScale = ContentScale.Crop,
                            alignment = Alignment.Center
                        )
                    }
                }
            }
            Text(
                text = stringResource(Res.string.greeting, user.name.getPrimaryName()),
                style = TextStyles.headingSmExtraBold(),
                color = Colors.White
            )
        }
    }
}

@Preview
@Composable
fun HomeTopBarPreviewDRAWABLE() {
    HomeTopBar(
        user = User(
            id = "1",
            name = "John Doe",
            image = UserImage(
                image = "0",
                type = ImageType.DRAWABLE
            ),
        )
    )
}

@Preview
@Composable
fun HomeTopBarPreviewURL() {
    HomeTopBar(
        user = User(
            id = "2",
            name = "John Doe 2",
            image = UserImage(
                image = "https://t3.ftcdn.net/jpg/02/99/04/20/360_F_299042079_vGBD7wIlSeNl7vOevWHiL93G4koMM967.jpg",
                type = ImageType.URL
            ),
        )
    )
}