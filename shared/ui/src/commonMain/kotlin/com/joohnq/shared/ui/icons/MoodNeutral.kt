package com.joohnq.shared.ui.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val MoodNeutral: ImageVector
    get() {
        if (_moodNeutral != null) {
            return _moodNeutral!!
        }
        _moodNeutral = Builder(
            name = "MoodNeutral",
            defaultWidth = 161.0.dp,
            defaultHeight = 160.0.dp,
            viewportWidth = 161.0f,
            viewportHeight = 160.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = EvenOdd
            ) {
                moveTo(120.5f, 46.67f)
                curveToRelative(3.68f, 0.0f, 6.67f, 2.98f, 6.67f, 6.66f)
                verticalLineToRelative(20.0f)
                curveToRelative(0.0f, 3.69f, -2.99f, 6.67f, -6.67f, 6.67f)
                curveToRelative(-3.68f, 0.0f, -6.67f, -2.98f, -6.67f, -6.67f)
                verticalLineToRelative(-20.0f)
                curveToRelative(0.0f, -3.68f, 2.99f, -6.66f, 6.67f, -6.66f)
                close()
                moveToRelative(-80.0f, 0.0f)
                curveToRelative(3.68f, 0.0f, 6.67f, 2.98f, 6.67f, 6.66f)
                verticalLineToRelative(20.0f)
                curveToRelative(0.0f, 3.69f, -2.99f, 6.67f, -6.67f, 6.67f)
                curveToRelative(-3.68f, 0.0f, -6.67f, -2.98f, -6.67f, -6.67f)
                verticalLineToRelative(-20.0f)
                curveToRelative(0.0f, -3.68f, 2.99f, -6.66f, 6.67f, -6.66f)
                close()
                moveToRelative(-6.67f, 60.0f)
                curveToRelative(0.0f, -3.68f, 2.99f, -6.67f, 6.67f, -6.67f)
                horizontalLineToRelative(80.0f)
                curveToRelative(3.68f, 0.0f, 6.67f, 2.99f, 6.67f, 6.67f)
                curveToRelative(0.0f, 3.68f, -2.99f, 6.66f, -6.67f, 6.66f)
                horizontalLineToRelative(-80.0f)
                curveToRelative(-3.68f, 0.0f, -6.67f, -2.98f, -6.67f, -6.66f)
                close()
            }
        }
            .build()
        return _moodNeutral!!
    }

private var _moodNeutral: ImageVector? = null
