package com.joohnq.stress_level.impl.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.joohnq.api.mapper.LocalDateTimeMapper.toMonthAbbreviatedAndDayString
import com.joohnq.shared_resources.components.layout.CardWithMoreMenuLayout
import com.joohnq.shared_resources.components.spacer.HorizontalSpacer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.paddingAllSmall
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource
import com.joohnq.stress_level.impl.ui.resource.StressorResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun StressLevelHistoryCard(
    modifier: Modifier = Modifier,
    item: StressLevelRecordResource,
    onDelete: () -> Unit = {},
) {
    CardWithMoreMenuLayout(
        modifier = modifier,
        onDelete = onDelete
    ) { modifier ->
        Row(
            modifier = modifier.fillMaxWidth().paddingAllSmall(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(Drawables.Icons.Filled.Warning),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = item.stressLevel.palette.color
            )
            HorizontalSpacer(20.dp)
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement =
                    Arrangement.spacedBy(
                        8.dp,
                        alignment = Alignment.CenterVertically
                    )
            ) {
                Text(
                    text = stringResource(item.stressLevel.subtitle),
                    style = TextStyles.textMdBold(),
                    color = Colors.Brown80
                )
                Text(
                    text =
                        if (item.stressors.isNotEmpty()) {
                            item.stressors.joinToString(", ")
                        } else {
                            stringResource(
                                StressorResource.InPeace.text
                            )
                        },
                    style = TextStyles.textSmMedium(),
                    color = Colors.Brown80,
                    overflow = TextOverflow.Ellipsis
                )
            }
            HorizontalSpacer(20.dp)
            Text(
                text = item.createdAt.toMonthAbbreviatedAndDayString(),
                style = TextStyles.textSmRegular(),
                color = Colors.Gray60
            )
        }
    }
}