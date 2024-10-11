package com.joohnq.moodapp.view.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Target: ImageVector
    get() {
        if (_target != null) {
            return _target!!
        }
        _target = Builder(
            name = "Target",
            defaultWidth = 37.0.dp,
            defaultHeight = 60.0.dp,
            viewportWidth = 37.0f,
            viewportHeight = 60.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF4B3425)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(0.999f, 37.841f)
                curveToRelative(-0.663f, 2.418f, -1.123f, 4.939f, -0.615f, 7.394f)
                curveToRelative(0.726f, 3.513f, 2.462f, 6.767f, 5.035f, 9.342f)
                curveTo(8.888f, 58.049f, 13.594f, 60.0f, 18.5f, 60.0f)
                curveToRelative(4.906f, 0.0f, 9.612f, -1.951f, 13.081f, -5.423f)
                curveToRelative(2.573f, -2.575f, 4.309f, -5.829f, 5.035f, -9.342f)
                curveToRelative(0.508f, -2.455f, 0.048f, -4.976f, -0.615f, -7.394f)
                lineTo(27.522f, 6.912f)
                curveToRelative(-2.515f, -9.176f, -15.529f, -9.176f, -18.044f, 0.0f)
                lineTo(0.999f, 37.841f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(19.0f, 38.0f)
                arcToRelative(6.0f, 6.0f, 0.0f, false, true, 6.0f, 6.0f)
                arcToRelative(6.0f, 6.0f, 0.0f, false, true, -6.0f, 6.0f)
                arcToRelative(6.0f, 6.0f, 0.0f, false, true, -6.0f, -6.0f)
                arcToRelative(6.0f, 6.0f, 0.0f, false, true, 6.0f, -6.0f)
                close()
            }
        }
            .build()
        return _target!!
    }

private var _target: ImageVector? = null
