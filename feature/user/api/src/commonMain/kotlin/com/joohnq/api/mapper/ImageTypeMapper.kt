package com.joohnq.api.mapper

import com.joohnq.api.entity.ImageType

object ImageTypeMapper {
    fun ImageType.toValue(): String = this.toString()

    fun String.toImageType(): ImageType = ImageType.valueOf(this)
}
