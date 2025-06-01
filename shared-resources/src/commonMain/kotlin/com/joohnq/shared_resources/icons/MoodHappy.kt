package com.joohnq.shared_resources.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val MoodHappy: ImageVector
    get() {
        if (_moodHappy != null) {
            return _moodHappy!!
        }
        _moodHappy = Builder(
            name = "MoodHappy",
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
                moveToRelative(73.92f, 48.85f)
                curveToRelative(3.56f, 0.95f, 5.67f, 4.61f, 4.72f, 8.17f)
                curveToRelative(-2.28f, 8.5f, -7.3f, 16.02f, -14.29f, 21.38f)
                curveToRelative(-6.99f, 5.36f, -15.54f, 8.26f, -24.35f, 8.26f)
                curveToRelative(-8.81f, 0.0f, -17.36f, -2.9f, -24.35f, -8.26f)
                curveToRelative(-6.99f, -5.36f, -12.01f, -12.88f, -14.29f, -21.38f)
                curveToRelative(-0.95f, -3.56f, 1.16f, -7.22f, 4.72f, -8.17f)
                curveToRelative(3.55f, -0.95f, 7.21f, 1.16f, 8.16f, 4.72f)
                curveToRelative(1.52f, 5.66f, 4.87f, 10.68f, 9.53f, 14.25f)
                curveToRelative(4.65f, 3.57f, 10.36f, 5.51f, 16.23f, 5.51f)
                curveToRelative(5.87f, 0.0f, 11.58f, -1.94f, 16.23f, -5.51f)
                curveToRelative(4.66f, -3.57f, 8.01f, -8.59f, 9.53f, -14.25f)
                curveToRelative(0.95f, -3.56f, 4.61f, -5.67f, 8.16f, -4.72f)
                close()
            }
        }
            .build()
        return _moodHappy!!
    }

private var _moodHappy: ImageVector? = null
