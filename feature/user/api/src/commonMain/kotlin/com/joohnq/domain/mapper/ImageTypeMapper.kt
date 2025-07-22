package com.joohnq.domain.mapper

import com.joohnq.domain.entity.ImageType

fun ImageType.toValue(): String = this.toString()

fun String.toImageType(): ImageType = ImageType.valueOf(this)