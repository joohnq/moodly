package com.joohnq.shared_resources.components.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.add
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.paddingAllSmall
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun GiganticCreateCard(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    onCreate: () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    Card(
        colors =
            CardColors(
                containerColor = Colors.Gray5,
                contentColor = Colors.Brown80,
                disabledContainerColor = Colors.Gray5,
                disabledContentColor = Colors.Brown80
            ),
        shape = Dimens.Shape.Large,
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().paddingAllSmall()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = title,
                        style = TextStyles.headingXsBold(),
                        color = Colors.Gray80
                    )
                    Text(
                        text = subtitle,
                        style = TextStyles.textSmRegular(),
                        color = Colors.Gray60
                    )
                }
                OutlinedIconButton(
                    onClick = onCreate,
                    shape = Dimens.Shape.Circle,
                    modifier = Modifier.size(48.dp),
                    border =
                        BorderStroke(
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
