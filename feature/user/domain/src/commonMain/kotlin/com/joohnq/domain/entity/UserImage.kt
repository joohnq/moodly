package com.joohnq.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class UserImage(
    val image: String? = null,
    val type: ImageType = ImageType.DRAWABLE,
)
