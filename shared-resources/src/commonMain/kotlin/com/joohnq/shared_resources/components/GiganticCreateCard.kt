package com.joohnq.shared_resources.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.add
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingAllSmall
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun GiganticCreateCard(
    modifier: Modifier = Modifier,
    containerColor: Color,
    title: String,
    subtitle: String,
    onCreate: () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    Card(
        colors = CardColors(
            containerColor = containerColor,
            contentColor = Colors.Brown80,
            disabledContainerColor = containerColor,
            disabledContentColor = Colors.Brown80
        ),
        shape = Dimens.Shape.Large,
        modifier = modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().paddingAllSmall(),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = title,
                        style = TextStyles.HeadingXsBold(),
                        color = Colors.Gray80
                    )
                    Text(
                        text = subtitle,
                        style = TextStyles.TextSmRegular(),
                        color = Colors.Gray60
                    )
                }
                OutlinedIconButton(
                    onClick = onCreate,
                    shape = Dimens.Shape.Circle,
                    modifier = Modifier.size(48.dp),
                    border = BorderStroke(
                        width = 1.dp,
                        color = Colors.Gray30
                    )
                ) {
                    Icon(
                        painter = painterResource(Drawables.Icons.Outlined.Add),
                        contentDescription = stringResource(Res.string.add),
                        tint = Colors.Gray80,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
            VerticalSpacer(16.dp)
            HorizontalDivider(modifier = Modifier.fillMaxWidth(), color = Colors.Gray20)
            VerticalSpacer(16.dp)
            content()
        }
    }
}

@Composable
fun GiganticSecondaryCard(
    modifier: Modifier = Modifier,
    containerColor: Color,
    title: String,
    subtitle: String,
    onClick: () -> Unit,
    secondary: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    Card(
        colors = CardColors(
            containerColor = containerColor,
            contentColor = Colors.Brown80,
            disabledContainerColor = containerColor,
            disabledContentColor = Colors.Brown80
        ),
        shape = Dimens.Shape.Large,
        modifier = modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().paddingAllSmall(),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = title,
                        style = TextStyles.HeadingXsBold(),
                        color = Colors.Gray80
                    )
                    Text(
                        text = subtitle,
                        style = TextStyles.TextSmRegular(),
                        color = Colors.Gray60
                    )
                }
                secondary()
            }
            VerticalSpacer(16.dp)
            HorizontalDivider(modifier = Modifier.fillMaxWidth(), color = Colors.Gray20)
            VerticalSpacer(16.dp)
            content()
        }
    }
}