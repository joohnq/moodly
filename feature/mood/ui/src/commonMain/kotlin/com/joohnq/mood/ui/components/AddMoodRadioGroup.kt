package com.joohnq.mood.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.mood.ui.mapper.getAllMoodResource
import com.joohnq.mood.ui.resource.MoodRecordResource
import com.joohnq.mood.ui.resource.MoodResource
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalExtraExtraSmall
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun AddMoodRadioGroup(
    modifier: Modifier = Modifier,
    selectedMood: MoodRecordResource,
    setSelectedMood: (MoodResource) -> Unit,
) {
    val resources by remember { mutableStateOf(getAllMoodResource()) }

    BoxWithConstraints(modifier = modifier) {
        val dividerWidth = (maxWidth - 180.dp - 40.dp) / 4
        LazyRow(
            modifier = Modifier.wrapContentSize(Alignment.Center).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            itemsIndexed(resources) { i, resource ->
                val selected = i <= selectedMood.mood.id
                Button(
                    modifier = Modifier.size(36.dp),
                    colors = ButtonColors(
                        containerColor = if (selected) Colors.White else selectedMood.mood.palette.moodScreenInactiveColor,
                        contentColor = selectedMood.mood.palette.moodScreenBackgroundColor,
                        disabledContainerColor = if (selected) Colors.White else selectedMood.mood.palette.moodScreenInactiveColor,
                        disabledContentColor = selectedMood.mood.palette.moodScreenBackgroundColor
                    ),
                    shape = Dimens.Shape.Circle,
                    onClick = {
                        setSelectedMood(resources[i])
                    },
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Box(
                        modifier = Modifier.size(8.dp).background(
                            color = selectedMood.mood.palette.moodScreenBackgroundColor,
                            shape = Dimens.Shape.Circle
                        )
                    )
                }

                if (i < resources.size - 1) {
                    Box(
                        modifier = Modifier.width(dividerWidth).height(10.dp)
                            .paddingHorizontalExtraExtraSmall()
                            .background(color = if (selectedMood.mood.id - 1 >= i) Colors.White else selectedMood.mood.palette.moodScreenInactiveColor)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun AddMoodRadioGroupPreviewDepressed() {
    AddMoodRadioGroup(
        selectedMood = MoodRecordResource(
            mood = MoodResource.Depressed
        ),
        setSelectedMood = {}
    )
}

@Preview
@Composable
fun AddMoodRadioGroupPreviewSad() {
    AddMoodRadioGroup(
        selectedMood = MoodRecordResource(
            mood = MoodResource.Sad
        ),
        setSelectedMood = {}
    )
}

@Preview
@Composable
fun AddMoodRadioGroupPreviewNeutral() {
    AddMoodRadioGroup(
        selectedMood = MoodRecordResource(
            mood = MoodResource.Neutral
        ),
        setSelectedMood = {}
    )
}

@Preview
@Composable
fun AddMoodRadioGroupPreviewHappy() {
    AddMoodRadioGroup(
        selectedMood = MoodRecordResource(
            mood = MoodResource.Happy
        ),
        setSelectedMood = {}
    )
}

@Preview
@Composable
fun AddMoodRadioGroupPreviewOverjoyed() {
    AddMoodRadioGroup(
        selectedMood = MoodRecordResource(
            mood = MoodResource.Overjoyed
        ),
        setSelectedMood = {}
    )
}