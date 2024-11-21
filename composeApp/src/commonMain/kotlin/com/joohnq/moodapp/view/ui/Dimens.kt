package com.joohnq.moodapp.view.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

object Dimens {
    val Icon = 24.dp

    object Padding {
        val HorizontalLarge = PaddingValues(horizontal = 24.dp)
        val HorizontalMedium = PaddingValues(horizontal = 20.dp)
        val HorizontalSmall = PaddingValues(horizontal = 16.dp)
        val HorizontalExtraExtraSmall = PaddingValues(horizontal = 4.dp)

        val VerticalExtraLarge = PaddingValues(vertical = 20.dp)
        val VerticalLarge = PaddingValues(vertical = 15.dp)
        val VerticalMedium = PaddingValues(vertical = 12.dp)
        val VerticalSmall = PaddingValues(vertical = 5.dp)
    }

    object Shape {
        val ExtraLarge = RoundedCornerShape(32.dp)
        val Large = RoundedCornerShape(24.dp)
        val Medium = RoundedCornerShape(20.dp)
        val Small = RoundedCornerShape(16.dp)
        val ExtraSmall = RoundedCornerShape(10.dp)
        val ExtraExtraSmall = RoundedCornerShape(4.dp)

        val Circle = CircleShape

        val BottomMedium = RoundedCornerShape(
            topStart = 0.dp,
            topEnd = 0.dp,
            bottomStart = 20.dp,
            bottomEnd = 20.dp
        )
    }
}

