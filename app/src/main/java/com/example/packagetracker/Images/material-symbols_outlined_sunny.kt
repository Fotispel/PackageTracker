package com.composables

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val Sunny: ImageVector
    get() {
        if (_Sunny != null) return _Sunny!!
        
        _Sunny = ImageVector.Builder(
            name = "Sunny",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000))
            ) {
                moveTo(440f, 200f)
                verticalLineToRelative(-160f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(160f)
                close()
                moveToRelative(266f, 110f)
                lineToRelative(-55f, -55f)
                lineToRelative(112f, -115f)
                lineToRelative(56f, 57f)
                close()
                moveToRelative(54f, 210f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(160f)
                verticalLineToRelative(80f)
                close()
                moveTo(440f, 920f)
                verticalLineToRelative(-160f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(160f)
                close()
                moveTo(254f, 308f)
                lineTo(140f, 197f)
                lineToRelative(57f, -56f)
                lineToRelative(113f, 113f)
                close()
                moveToRelative(508f, 512f)
                lineTo(651f, 705f)
                lineToRelative(54f, -54f)
                lineToRelative(114f, 110f)
                close()
                moveTo(40f, 520f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(160f)
                verticalLineToRelative(80f)
                close()
                moveToRelative(157f, 300f)
                lineToRelative(-56f, -57f)
                lineToRelative(112f, -112f)
                lineToRelative(29f, 27f)
                lineToRelative(29f, 28f)
                close()
                moveToRelative(283f, -100f)
                quadToRelative(-100f, 0f, -170f, -70f)
                reflectiveQuadToRelative(-70f, -170f)
                reflectiveQuadToRelative(70f, -170f)
                reflectiveQuadToRelative(170f, -70f)
                reflectiveQuadToRelative(170f, 70f)
                reflectiveQuadToRelative(70f, 170f)
                reflectiveQuadToRelative(-70f, 170f)
                reflectiveQuadToRelative(-170f, 70f)
                moveToRelative(0f, -80f)
                quadToRelative(66f, 0f, 113f, -47f)
                reflectiveQuadToRelative(47f, -113f)
                reflectiveQuadToRelative(-47f, -113f)
                reflectiveQuadToRelative(-113f, -47f)
                reflectiveQuadToRelative(-113f, 47f)
                reflectiveQuadToRelative(-47f, 113f)
                reflectiveQuadToRelative(47f, 113f)
                reflectiveQuadToRelative(113f, 47f)
                moveToRelative(0f, -160f)
            }
        }.build()
        
        return _Sunny!!
    }

private var _Sunny: ImageVector? = null

