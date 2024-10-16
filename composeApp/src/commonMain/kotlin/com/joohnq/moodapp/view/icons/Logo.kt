package com.joohnq.moodapp.view.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kotlin.Suppress

val LogoImageVector: ImageVector
    get() {
        if (_Logo != null) {
            return _Logo!!
        }
        _Logo = ImageVector.Builder(
            name = "Logo",
            defaultWidth = 48.dp,
            defaultHeight = 48.dp,
            viewportWidth = 48f,
            viewportHeight = 48f
        ).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF))) {
                moveTo(24f, 20f)
                curveTo(28.418f, 20f, 32f, 16.418f, 32f, 12f)
                curveTo(32f, 7.582f, 28.418f, 4f, 24f, 4f)
                curveTo(19.582f, 4f, 16f, 7.582f, 16f, 12f)
                curveTo(16f, 16.418f, 19.582f, 20f, 24f, 20f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFFFFFF))) {
                moveTo(24f, 44f)
                curveTo(28.418f, 44f, 32f, 40.418f, 32f, 36f)
                curveTo(32f, 31.582f, 28.418f, 28f, 24f, 28f)
                curveTo(19.582f, 28f, 16f, 31.582f, 16f, 36f)
                curveTo(16f, 40.418f, 19.582f, 44f, 24f, 44f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFFFFFF))) {
                moveTo(36f, 32f)
                curveTo(31.582f, 32f, 28f, 28.418f, 28f, 24f)
                curveTo(28f, 19.582f, 31.582f, 16f, 36f, 16f)
                curveTo(40.418f, 16f, 44f, 19.582f, 44f, 24f)
                curveTo(44f, 28.418f, 40.418f, 32f, 36f, 32f)
                close()
            }
            path(fill = SolidColor(Color(0xFFFFFFFF))) {
                moveTo(4f, 24f)
                curveTo(4f, 28.418f, 7.582f, 32f, 12f, 32f)
                curveTo(16.418f, 32f, 20f, 28.418f, 20f, 24f)
                curveTo(20f, 19.582f, 16.418f, 16f, 12f, 16f)
                curveTo(7.582f, 16f, 4f, 19.582f, 4f, 24f)
                close()
            }
        }.build()

        return _Logo!!
    }

@Suppress("ObjectPropertyName")
private var _Logo: ImageVector? = null
