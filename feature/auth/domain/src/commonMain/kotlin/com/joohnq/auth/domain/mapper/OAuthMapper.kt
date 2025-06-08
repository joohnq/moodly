package com.joohnq.auth.domain.mapper

import com.joohnq.auth.domain.entity.OAuthUser
import com.joohnq.domain.entity.ImageType
import com.joohnq.domain.entity.User
import com.joohnq.domain.entity.UserImage


fun OAuthUser.toUser(): User {
    return User(
        email = email,
        name = name,
        image = UserImage(image = image, type = ImageType.URL)
    )
}