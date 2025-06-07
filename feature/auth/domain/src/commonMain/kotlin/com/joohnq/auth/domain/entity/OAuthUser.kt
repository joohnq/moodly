package com.joohnq.auth.domain.entity

import com.joohnq.domain.entity.ImageType
import com.joohnq.domain.entity.User
import com.joohnq.domain.entity.UserImage

data class OAuthUser(
    val id: String?,
    val token: String,
    val accessToken: String?,
    val email: String?,
    val name: String?,
    val image: String?
)