package com.joohnq.api.mapper

import com.joohnq.api.mapper.DoubleMapper.toDegrees
import com.joohnq.api.mapper.DoubleMapper.toRadians

object FloatMapper {
    fun Float.toRadians(): Double = this.toDouble().toRadians()

    fun Float.toDegrees(): Double = this.toDouble().toDegrees()
}
