package com.joohnq.mood.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.mood.ui.MoodResource
import com.joohnq.shared.ui.theme.Colors
import com.joohnq.shared.ui.theme.Dimens
import com.joohnq.shared.ui.theme.PaddingModifier.Companion.paddingHorizontalExtraExtraSmall

@Composable
fun AddMoodRadioGroup(
    moodsSize: Int,
    moodIndex: Int,
    selectedMood: MoodResource,
    setSelectedMood: (Int) -> Unit,
) {
    BoxWithConstraints {
        val dividerWidth = (maxWidth - 180.dp - 40.dp) / 4
        LazyRow(
            modifier = Modifier.wrapContentSize(Alignment.Center).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            items(moodsSize, key = { it }) { i ->
                Button(
                    modifier = Modifier.size(36.dp),
                    colors = ButtonColors(
                        containerColor = if (i <= moodIndex) Colors.White else selectedMood.palette.moodScreenInactiveColor,
                        contentColor = selectedMood.palette.moodScreenBackgroundColor,
                        disabledContainerColor = if (i <= moodIndex) Colors.White else selectedMood.palette.moodScreenInactiveColor,
                        disabledContentColor = selectedMood.palette.moodScreenBackgroundColor
                    ),
                    shape = Dimens.Shape.Circle,
                    onClick = {
                        setSelectedMood(i)
                    },
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Box(
                        modifier = Modifier.size(8.dp).background(
                            color = selectedMood.palette.moodScreenBackgroundColor,
                            shape = Dimens.Shape.Circle
                        )
                    )
                }

                if (i < moodsSize - 1) {
                    Box(
                        modifier = Modifier.width(dividerWidth).height(10.dp)
                            .paddingHorizontalExtraExtraSmall()
                            .background(color = if (moodIndex - 1 >= i) Colors.White else selectedMood.palette.moodScreenInactiveColor)
                    )
                }
            }
        }
    }
}