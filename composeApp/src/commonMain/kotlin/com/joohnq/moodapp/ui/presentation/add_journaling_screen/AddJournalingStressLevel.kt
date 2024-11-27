package com.joohnq.moodapp.ui.presentation.add_journaling_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.moodapp.ui.components.AddJournalingStressLevelThumb
import com.joohnq.moodapp.ui.theme.Colors
import com.joohnq.moodapp.ui.theme.ComponentColors
import com.joohnq.moodapp.ui.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.moodapp.ui.theme.TextStyles
import com.joohnq.moodapp.viewmodel.AddingJournalingIntent
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.five_number
import moodapp.composeapp.generated.resources.one_number
import moodapp.composeapp.generated.resources.three_number
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddJournalingStressLevel(
    sliderValue: Float,
    onAddingAction: (AddingJournalingIntent) -> Unit
) {
    Column {
        Slider(
            value = sliderValue,
            onValueChange = {
                onAddingAction(
                    AddingJournalingIntent.UpdateSliderValue(it)
                )
            },
            steps = 3,
            valueRange = 0f..100f,
            colors = ComponentColors.Slider.AddJournalingStressLevel(),
            thumb = { AddJournalingStressLevelThumb() }
        )
        Row(
            modifier = Modifier.fillMaxWidth().paddingHorizontalMedium(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(Res.string.one_number),
                style = TextStyles.TextMdBold(),
                color = Colors.Brown100Alpha64
            )
            Text(
                text = stringResource(Res.string.three_number),
                style = TextStyles.TextMdBold(),
                color = Colors.Brown100Alpha64
            )
            Text(
                text = stringResource(Res.string.five_number),
                style = TextStyles.TextMdBold(),
                color = Colors.Brown100Alpha64
            )
        }
    }
}