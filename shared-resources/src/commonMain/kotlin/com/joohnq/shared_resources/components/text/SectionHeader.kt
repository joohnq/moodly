package com.joohnq.shared_resources.components.text

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.see_more
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SectionHeader(
    modifier: Modifier = Modifier,
    title: StringResource,
    onSeeMore: () -> Unit = {},
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(title),
            style = TextStyles.textLgExtraBold(),
            color = Colors.Brown80,
            modifier = Modifier.padding(vertical = 20.dp)
        )
        TextButton(
            onClick = onSeeMore,
            contentPadding = PaddingValues(horizontal = 5.dp, vertical = 2.dp),
            shape = Dimens.Shape.Circle,
            elevation =
                ButtonDefaults.buttonElevation(
                    defaultElevation = 0.dp,
                    pressedElevation = 0.dp,
                    focusedElevation = 0.dp,
                    hoveredElevation = 0.dp,
                    disabledElevation = 0.dp
                ),
            colors =
                ButtonColors(
                    containerColor = Colors.Transparent,
                    contentColor = Colors.Brown80,
                    disabledContainerColor = Colors.Transparent,
                    disabledContentColor = Colors.Brown80
                )
        ) {
            Text(
                modifier = Modifier,
                text = stringResource(Res.string.see_more),
                style = TextStyles.textSmMedium()
            )
        }
    }
}

@Composable
fun SectionHeader(
    modifier: Modifier = Modifier,
    title: StringResource,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(title),
            style = TextStyles.textLgExtraBold(),
            color = Colors.Brown80,
            modifier = Modifier.padding(vertical = 20.dp)
        )
    }
}
