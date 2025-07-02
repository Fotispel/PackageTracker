package com.composables

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Moon: ImageVector
    get() {
        if (_Moon != null) return _Moon!!
        
        _Moon = ImageVector.Builder(
            name = "Moon",
            defaultWidth = 16.dp,
            defaultHeight = 16.dp,
            viewportWidth = 16f,
            viewportHeight = 16f
        ).apply {
            path(
                fill = SolidColor(Color.Black)
            ) {
                moveTo(6f, 0.278f)
                arcToRelative(0.77f, 0.77f, 0f, false, true, 0.08f, 0.858f)
                arcToRelative(7.2f, 7.2f, 0f, false, false, -0.878f, 3.46f)
                curveToRelative(0f, 4.021f, 3.278f, 7.277f, 7.318f, 7.277f)
                quadToRelative(0.792f, -0.001f, 1.533f, -0.16f)
                arcToRelative(0.79f, 0.79f, 0f, false, true, 0.81f, 0.316f)
                arcToRelative(0.73f, 0.73f, 0f, false, true, -0.031f, 0.893f)
                arcTo(8.35f, 8.35f, 0f, false, true, 8.344f, 16f)
                curveTo(3.734f, 16f, 0f, 12.286f, 0f, 7.71f)
                curveTo(0f, 4.266f, 2.114f, 1.312f, 5.124f, 0.06f)
                arcTo(0.75f, 0.75f, 0f, false, true, 6f, 0.278f)
                moveTo(4.858f, 1.311f)
                arcTo(7.27f, 7.27f, 0f, false, false, 1.025f, 7.71f)
                curveToRelative(0f, 4.02f, 3.279f, 7.276f, 7.319f, 7.276f)
                arcToRelative(7.32f, 7.32f, 0f, false, false, 5.205f, -2.162f)
                quadToRelative(-0.506f, 0.063f, -1.029f, 0.063f)
                curveToRelative(-4.61f, 0f, -8.343f, -3.714f, -8.343f, -8.29f)
                curveToRelative(0f, -1.167f, 0.242f, -2.278f, 0.681f, -3.286f)
            }
        }.build()
        
        return _Moon!!
    }

private var _Moon: ImageVector? = null

