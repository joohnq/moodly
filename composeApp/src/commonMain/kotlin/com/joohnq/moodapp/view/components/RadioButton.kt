package com.joohnq.moodapp.view.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.entities.Icon
import com.joohnq.moodapp.entities.IconAndTextRadioButtonColors
import com.joohnq.moodapp.entities.MedicationsSupplements
import com.joohnq.moodapp.entities.PhysicalSymptoms
import com.joohnq.moodapp.entities.ProfessionalHelp
import com.joohnq.moodapp.view.constants.Colors
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun TextRadioButton(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean,
    selectedBackground: Color,
    selectedContent: Color,
    unSelectedBackground: Color,
    unSelectedContent: Color,
    shape: Shape,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.fillMaxWidth().height(56.dp),
        shape = shape,
        colors = ButtonColors(
            containerColor = if (selected) selectedBackground else unSelectedBackground,
            contentColor = if (selected) selectedContent else unSelectedContent,
            disabledContainerColor = if (selected) selectedBackground else unSelectedBackground,
            disabledContentColor = if (selected) selectedContent else unSelectedContent
        ),
        onClick = onClick
    ) {
        Text(
            text = text,
            style = TextStyles.WelcomeScreenButton()
        )
    }
}

@Composable
fun IconAndTextRadioButtonHorizontal(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    text: String,
    icon: Icon,
    selected: Boolean,
    iconAndTextRadioButtonColors: IconAndTextRadioButtonColors,
    shape: Shape,
    textStyle: TextStyle,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        shape = shape,
        colors = ButtonColors(
            containerColor = if (selected) iconAndTextRadioButtonColors.selectedBackgroundColor else iconAndTextRadioButtonColors.unSelectedBackgroundColor,
            contentColor = if (selected) iconAndTextRadioButtonColors.selectedContentColor else iconAndTextRadioButtonColors.unSelectedContentColor,
            disabledContainerColor = if (selected) iconAndTextRadioButtonColors.selectedBackgroundColor else iconAndTextRadioButtonColors.unSelectedBackgroundColor,
            disabledContentColor = if (selected) iconAndTextRadioButtonColors.selectedContentColor else iconAndTextRadioButtonColors.unSelectedContentColor
        ),
        border = if (selected) BorderStroke(
            color = iconAndTextRadioButtonColors.selectedBorderColor,
            width = 4.dp
        ) else null,
        contentPadding = PaddingValues(0.dp),
        onClick = onClick
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(paddingValues),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(icon.icon),
                    contentDescription = stringResource(icon.contentDescription),
                    tint = if (selected) iconAndTextRadioButtonColors.selectedContentColor else iconAndTextRadioButtonColors.unSelectedContentColor,
                    modifier = icon.modifier
                )
                Text(
                    text = text,
                    style = textStyle
                )
            }
            RadioButton(
                selected = selected,
                onClick = onClick,
                colors = RadioButtonDefaults.colors(
                    selectedColor = iconAndTextRadioButtonColors.selectedContentColor,
                    unselectedColor = iconAndTextRadioButtonColors.unSelectedContentColor,
                )
            )
        }
    }
}

@Composable
fun IconAndTextRadioButtonVertical(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    text: String,
    icon: Icon,
    selected: Boolean,
    iconAndTextRadioButtonColors: IconAndTextRadioButtonColors,
    shape: Shape,
    textStyle: TextStyle,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        shape = shape,
        colors = ButtonColors(
            containerColor = if (selected) iconAndTextRadioButtonColors.selectedBackgroundColor else iconAndTextRadioButtonColors.unSelectedBackgroundColor,
            contentColor = if (selected) iconAndTextRadioButtonColors.selectedContentColor else iconAndTextRadioButtonColors.unSelectedContentColor,
            disabledContainerColor = if (selected) iconAndTextRadioButtonColors.selectedBackgroundColor else iconAndTextRadioButtonColors.unSelectedBackgroundColor,
            disabledContentColor = if (selected) iconAndTextRadioButtonColors.selectedContentColor else iconAndTextRadioButtonColors.unSelectedContentColor
        ),
        border = if (selected) BorderStroke(
            color = iconAndTextRadioButtonColors.selectedBorderColor,
            width = 4.dp
        ) else null,
        contentPadding = PaddingValues(0.dp),
        onClick = onClick
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            horizontalAlignment = Alignment.Start
        ) {
            Icon(
                painter = painterResource(icon.icon),
                contentDescription = stringResource(icon.contentDescription),
                tint = if (selected) iconAndTextRadioButtonColors.selectedContentColor else iconAndTextRadioButtonColors.unSelectedContentColor,
                modifier = icon.modifier
            )
            Text(
                text = text,
                style = textStyle.copy(color = if (selected) iconAndTextRadioButtonColors.selectedContentColor else iconAndTextRadioButtonColors.unSelectedContentColor)
            )
        }
    }
}

@Composable
fun PhysicalSymptomsRadioButton(
    modifier: Modifier = Modifier,
    option: PhysicalSymptoms,
    selected: Boolean,
    onClick: () -> Unit
) {
    IconAndTextRadioButtonHorizontal(
        modifier = modifier.fillMaxWidth(),
        paddingValues = PaddingValues(all = 16.dp),
        text = stringResource(option.text),
        icon = option.icon.copy(modifier = Modifier.size(24.dp)),
        selected = selected,
        iconAndTextRadioButtonColors = IconAndTextRadioButtonColors(
            selectedBackgroundColor = Colors.Green50,
            selectedContentColor = Colors.White,
            unSelectedContentColor = Colors.Brown80,
            unSelectedBackgroundColor = Colors.White,
            selectedBorderColor = Colors.Green50Alpha25,
        ),
        shape = RoundedCornerShape(20.dp),
        textStyle = TextStyles.WelcomeScreenButton(),
        onClick = onClick
    )
}

@Composable
fun MedicationsSupplementsRadioButton(
    modifier: Modifier = Modifier,
    option: MedicationsSupplements,
    selected: Boolean,
    onClick: () -> Unit
) {
    IconAndTextRadioButtonVertical(
        modifier = modifier.fillMaxSize().aspectRatio(1f),
        paddingValues = PaddingValues(all = 16.dp),
        text = stringResource(option.text),
        icon = option.icon.copy(modifier = Modifier.size(24.dp)),
        selected = selected,
        iconAndTextRadioButtonColors = IconAndTextRadioButtonColors(
            selectedBackgroundColor = Colors.Green50,
            selectedContentColor = Colors.White,
            unSelectedContentColor = Colors.Brown80,
            unSelectedBackgroundColor = Colors.White,
            selectedBorderColor = Colors.Green50Alpha25,
        ),
        shape = RoundedCornerShape(20.dp),
        textStyle = TextStyles.OnboardingMedicationsGridItem(),
        onClick = onClick
    )
}

@Composable
fun ProfessionalHelpRadioButton(
    modifier: Modifier = Modifier,
    option: ProfessionalHelp,
    selected: Boolean,
    onClick: () -> Unit
) {
    TextRadioButton(
        modifier = modifier,
        text = stringResource(option.text),
        selected = selected,
        selectedBackground = Colors.Green50,
        selectedContent = Colors.White,
        unSelectedContent = Colors.Brown80,
        unSelectedBackground = Colors.White,
        shape = CircleShape,
        onClick = onClick
    )
}